package com.example.p3l_ajr_0171;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.p3l_ajr_0171.api.LaporanApi;
import com.example.p3l_ajr_0171.databinding.ActivityManagerMenuBinding;
import com.example.p3l_ajr_0171.entitylaporan.Laporan5CustomerTransaksi;
import com.example.p3l_ajr_0171.entitylaporan.Laporan5DriverTransaksi;
import com.example.p3l_ajr_0171.entitylaporan.LaporanDetailPendapatan;
import com.example.p3l_ajr_0171.entitylaporan.LaporanPenyewaanMobil;
import com.example.p3l_ajr_0171.entitylaporan.LaporanPerformaDriver;
import com.example.p3l_ajr_0171.preferences.UserPreference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.example.p3l_ajr_0171.responselaporan.Laporan5CustomerTrnsResponse;
import com.example.p3l_ajr_0171.responselaporan.Laporan5DriverTrnsResponse;
import com.example.p3l_ajr_0171.responselaporan.LaporanDetailPendapatanResponse;
import com.example.p3l_ajr_0171.responselaporan.LaporanPenyewaanMobilResponse;
import com.example.p3l_ajr_0171.responselaporan.LaporanPerformaDriverResponse;
import com.google.gson.Gson;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.json.JSONObject;

public class ManagerMenuActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityManagerMenuBinding binding;
    private RequestQueue queue;
    private UserPreference userPreference;
    private int mYear,mMonth,mDay;
    private Calendar calendarToUse;

    // Buat Laporan
    private List<Laporan5CustomerTransaksi> laporan5CustomerTransaksi = null;
    private List<Laporan5DriverTransaksi> laporan5DriverTransaksi = null;
    private List<LaporanDetailPendapatan> laporanDetailPendapatan = null;
    private List<LaporanPenyewaanMobil> laporanPenyewaanMobil = null;
    private List<LaporanPerformaDriver> laporanPerformaDriver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manager_menu);
        userPreference = new UserPreference(this);
        queue = Volley.newRequestQueue(this.getApplicationContext());

        binding.btn5CustomerTransaksi.setOnClickListener(this);
        binding.btn5Driver.setOnClickListener(this);
        binding.btnDetailPendapatan.setOnClickListener(this);
        binding.btnPerformaDrv.setOnClickListener(this);
        binding.btnSewaMobil.setOnClickListener(this);
        binding.btnLogout.setOnClickListener(this);

        CheckLogin();

        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // myCalendar.add(Calendar.DATE, 0);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                binding.inputTglLaporan.getEditText().setText(sdf.format(myCalendar.getTime()));
            }
        };

        binding.btnTglLaporan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(ManagerMenuActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox
                                view.updateDate(mYear,mMonth,mDay);
                                Calendar cal = Calendar.getInstance();
                                cal.set(year, monthOfYear, dayOfMonth);
                                calendarToUse = cal;

                                String myFormat = "yyyy-MM-dd"; //In which you need put here
                                SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                                binding.inputTglLaporan.getEditText().setText(sdf.format(cal.getTime()));

                            }
                        }, mYear, mMonth, mDay);
                dpd.show();

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnLogout)
        {
           userPreference.Logout();
           CheckLogin();
        }
        else
        {
            if(calendarToUse != null)
            {
                if(view.getId() == R.id.btn5CustomerTransaksi)
                {
                    try {
                        GetLaporan5TransaksiCustomer();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else if(view.getId() == R.id.btn5Driver)
                {
                    try {
                        GetLaporan5TransaksiDriver();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else if(view.getId() == R.id.btnDetailPendapatan)
                {
                    try {
                        GetLaporanDetailPendapatan();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else if(view.getId() == R.id.btnPerformaDrv)
                {
                    try {
                        GetLaporanPerformaDriver();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else if(view.getId() == R.id.btnSewaMobil)
                {
                    try {
                        GetLaporanSewaMobil();
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else
            {
                Toast.makeText(this, "Pilihlah bulan dan tahun untuk laporan!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void CheckLogin()
    {
        if(!userPreference.CheckLogin())
        {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    private void cetakLaporan5Customer() throws FileNotFoundException, DocumentException
    {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        if(!folder.exists())
        {
            folder.mkdir();
        }

        String pdfName = "Laporan5CustomerDenganJumlahTransaksiTerbanyak" + ".pdf";

        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Paragraph judul = new Paragraph("Laporan 5 Customer dengan Jumlah Transaksi Terbanyak \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK));

        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        PdfPTable table = new PdfPTable((new float[]{16, 8}));

        table.getDefaultCell().setFixedHeight(50);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

//        PdfPCell cellSupplier = new PdfPCell();
//        cellSupplier.setPaddingLeft(20);
//        cellSupplier.setPaddingBottom(10);
//        cellSupplier.setBorder(Rectangle.NO_BORDER);

        String bulan = new SimpleDateFormat("MMM-yyyy", Locale.getDefault()).format(calendarToUse.getTime());


        Paragraph nomorTanggal = new Paragraph("Bulan & Tahun: " + bulan + "\n\n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK));

//        cellSupplier.addElement(nomorTanggal);
        nomorTanggal.setPaddingTop(5);
        nomorTanggal.setAlignment(Element.ALIGN_CENTER);
//        table.addCell(cellSupplier);
        document.add(nomorTanggal);


        com.itextpdf.text.Font font = new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPTable tableHeader = new PdfPTable(new float[]{2, 2});

        tableHeader.setPaddingTop(45);
        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);

        PdfPCell h1 = new PdfPCell(new Phrase("Nama Customer"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);

        PdfPCell h2 = new PdfPCell(new Phrase("Jumlah Transaksi"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);

//        PdfPCell h3 = new PdfPCell(new Phrase("Jabatan"));
//        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
//        h3.setPaddingBottom(5);
//
//        PdfPCell h4 = new PdfPCell(new Phrase("Tahun Masuk"));
//        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
//        h4.setPaddingBottom(5);
//
//        PdfPCell h5 = new PdfPCell(new Phrase("Gaji"));
//        h5.setHorizontalAlignment(Element.ALIGN_CENTER);
//        h5.setPaddingBottom(5);

        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
//        tableHeader.addCell(h3);
//        tableHeader.addCell(h4);
//        tableHeader.addCell(h5);

        for(PdfPCell cell : tableHeader.getRow(0).getCells())
        {
            cell.setBackgroundColor(BaseColor.GRAY);
        }

        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{2, 2});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        for(Laporan5CustomerTransaksi lap : laporan5CustomerTransaksi)
        {
            tableData.addCell(lap.getNamaCust());
            tableData.addCell(String.valueOf(lap.getJumlahTransaksi()));
        }

        document.add(tableData);

        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "PDF Berhasil dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakLaporan5Driver() throws FileNotFoundException, DocumentException
    {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        if(!folder.exists())
        {
            folder.mkdir();
        }

        String pdfName = "Laporan5DriverDenganJumlahTransaksiTerbanyak" + ".pdf";

        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Paragraph judul = new Paragraph("Laporan 5 Driver dengan Jumlah Transaksi Terbanyak \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK));

        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        PdfPTable table = new PdfPTable((new float[]{16, 8}));

        table.getDefaultCell().setFixedHeight(50);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

//        PdfPCell cellSupplier = new PdfPCell();
//        cellSupplier.setPaddingLeft(20);
//        cellSupplier.setPaddingBottom(10);
//        cellSupplier.setBorder(Rectangle.NO_BORDER);

        String bulan = new SimpleDateFormat("MMM-yyyy", Locale.getDefault()).format(calendarToUse.getTime());


        Paragraph nomorTanggal = new Paragraph("Bulan & Tahun: " + bulan + "\n\n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK));

//        cellSupplier.addElement(nomorTanggal);
        nomorTanggal.setPaddingTop(5);
        nomorTanggal.setAlignment(Element.ALIGN_CENTER);
//        table.addCell(cellSupplier);
        document.add(nomorTanggal);


        com.itextpdf.text.Font font = new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPTable tableHeader = new PdfPTable(new float[]{3, 3, 3});

        tableHeader.setPaddingTop(45);
        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);

        PdfPCell h1 = new PdfPCell(new Phrase("Id Driver"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);

        PdfPCell h2 = new PdfPCell(new Phrase("Nama Driver"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);

        PdfPCell h3 = new PdfPCell(new Phrase("Jumlah Transaksi"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);
//
//        PdfPCell h4 = new PdfPCell(new Phrase("Tahun Masuk"));
//        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
//        h4.setPaddingBottom(5);
//
//        PdfPCell h5 = new PdfPCell(new Phrase("Gaji"));
//        h5.setHorizontalAlignment(Element.ALIGN_CENTER);
//        h5.setPaddingBottom(5);

        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
//        tableHeader.addCell(h4);
//        tableHeader.addCell(h5);

        for(PdfPCell cell : tableHeader.getRow(0).getCells())
        {
            cell.setBackgroundColor(BaseColor.GRAY);
        }

        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{3, 3, 3});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        for(Laporan5DriverTransaksi lap : laporan5DriverTransaksi)
        {
            tableData.addCell(lap.getIdDriverGenerated());
            tableData.addCell(lap.getNamaDrv());
            tableData.addCell(String.valueOf(lap.getJumlahTransaksi()));
        }

        document.add(tableData);

        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "PDF Berhasil dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakLaporanDetailPendapatan() throws FileNotFoundException, DocumentException
    {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        if(!folder.exists())
        {
            folder.mkdir();
        }

        String pdfName = "LaporanDetailPendapatan" + ".pdf";

        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Paragraph judul = new Paragraph("Laporan Detail Pendapatan Transaksi \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK));

        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        PdfPTable table = new PdfPTable((new float[]{16, 8}));

        table.getDefaultCell().setFixedHeight(50);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

//        PdfPCell cellSupplier = new PdfPCell();
//        cellSupplier.setPaddingLeft(20);
//        cellSupplier.setPaddingBottom(10);
//        cellSupplier.setBorder(Rectangle.NO_BORDER);

        String bulan = new SimpleDateFormat("MMM-yyyy", Locale.getDefault()).format(calendarToUse.getTime());


        Paragraph nomorTanggal = new Paragraph("Bulan & Tahun: " + bulan + "\n\n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK));

//        cellSupplier.addElement(nomorTanggal);
        nomorTanggal.setPaddingTop(5);
        nomorTanggal.setAlignment(Element.ALIGN_CENTER);
//        table.addCell(cellSupplier);
        document.add(nomorTanggal);


        com.itextpdf.text.Font font = new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPTable tableHeader = new PdfPTable(new float[]{5, 5, 5, 5, 5});

        tableHeader.setPaddingTop(45);
        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);

        PdfPCell h1 = new PdfPCell(new Phrase("Nama Customer"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);

        PdfPCell h2 = new PdfPCell(new Phrase("Nama Mobil"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);

        PdfPCell h3 = new PdfPCell(new Phrase("Jenis Transaksi"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);

        PdfPCell h4 = new PdfPCell(new Phrase("Jumlah Transaksi"));
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setPaddingBottom(5);

        PdfPCell h5 = new PdfPCell(new Phrase("Pendapatan"));
        h5.setHorizontalAlignment(Element.ALIGN_CENTER);
        h5.setPaddingBottom(5);

        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
        tableHeader.addCell(h4);
        tableHeader.addCell(h5);

        for(PdfPCell cell : tableHeader.getRow(0).getCells())
        {
            cell.setBackgroundColor(BaseColor.GRAY);
        }

        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{5, 5, 5, 5, 5});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        for(LaporanDetailPendapatan lap : laporanDetailPendapatan)
        {
            tableData.addCell(lap.getNamaCust());
            tableData.addCell(lap.getNamaMbl());
            tableData.addCell(lap.getJenisTransaksi());
            tableData.addCell(String.valueOf(lap.getJumlahTransaksi()));
            tableData.addCell(String.valueOf(lap.getPendapatan()));
        }

        document.add(tableData);

        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "PDF Berhasil dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakLaporanPerformaDriver() throws FileNotFoundException, DocumentException
    {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        if(!folder.exists())
        {
            folder.mkdir();
        }

        String pdfName = "LaporanPerformaDriver" + ".pdf";

        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Paragraph judul = new Paragraph("Laporan Performa Driver \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK));

        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        PdfPTable table = new PdfPTable((new float[]{16, 8}));

        table.getDefaultCell().setFixedHeight(50);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

//        PdfPCell cellSupplier = new PdfPCell();
//        cellSupplier.setPaddingLeft(20);
//        cellSupplier.setPaddingBottom(10);
//        cellSupplier.setBorder(Rectangle.NO_BORDER);

        String bulan = new SimpleDateFormat("MMM-yyyy", Locale.getDefault()).format(calendarToUse.getTime());


        Paragraph nomorTanggal = new Paragraph("Bulan & Tahun: " + bulan + "\n\n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK));

//        cellSupplier.addElement(nomorTanggal);
        nomorTanggal.setPaddingTop(5);
        nomorTanggal.setAlignment(Element.ALIGN_CENTER);
//        table.addCell(cellSupplier);
        document.add(nomorTanggal);


        com.itextpdf.text.Font font = new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPTable tableHeader = new PdfPTable(new float[]{4, 4, 4, 4});

        tableHeader.setPaddingTop(45);
        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);

        PdfPCell h1 = new PdfPCell(new Phrase("Id Driver"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);

        PdfPCell h2 = new PdfPCell(new Phrase("Nama Driver"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);

        PdfPCell h3 = new PdfPCell(new Phrase("Jumlah Transaksi"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);

        PdfPCell h4 = new PdfPCell(new Phrase("Rerata Rating"));
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setPaddingBottom(5);

//        PdfPCell h5 = new PdfPCell(new Phrase("Pendapatan"));
//        h5.setHorizontalAlignment(Element.ALIGN_CENTER);
//        h5.setPaddingBottom(5);

        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
        tableHeader.addCell(h4);
//        tableHeader.addCell(h5);

        for(PdfPCell cell : tableHeader.getRow(0).getCells())
        {
            cell.setBackgroundColor(BaseColor.GRAY);
        }

        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{4, 4, 4, 4});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        for(LaporanPerformaDriver lap : laporanPerformaDriver)
        {
            tableData.addCell(lap.getIdDriverGenerated());
            tableData.addCell(lap.getNamaDrv());
            tableData.addCell(String.valueOf(lap.getJumlahTransaksi()));
            tableData.addCell(String.valueOf(lap.getRerataRating()));
        }

        document.add(tableData);

        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "PDF Berhasil dibuat", Toast.LENGTH_SHORT).show();
    }

    private void cetakLaporanSewaMobil() throws FileNotFoundException, DocumentException
    {
        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        if(!folder.exists())
        {
            folder.mkdir();
        }

        String pdfName = "LaporanSewaMobil" + ".pdf";

        File pdfFile = new File(folder.getAbsolutePath(), pdfName);
        OutputStream outputStream = new FileOutputStream(pdfFile);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Paragraph judul = new Paragraph("Laporan Penyewaan Mobil \n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK));

        judul.setAlignment(Element.ALIGN_CENTER);
        document.add(judul);

        PdfPTable table = new PdfPTable((new float[]{16, 8}));

        table.getDefaultCell().setFixedHeight(50);
        table.setTotalWidth(PageSize.A4.getWidth());
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

//        PdfPCell cellSupplier = new PdfPCell();
//        cellSupplier.setPaddingLeft(20);
//        cellSupplier.setPaddingBottom(10);
//        cellSupplier.setBorder(Rectangle.NO_BORDER);

        String bulan = new SimpleDateFormat("MMM-yyyy", Locale.getDefault()).format(calendarToUse.getTime());


        Paragraph nomorTanggal = new Paragraph("Bulan & Tahun: " + bulan + "\n\n\n",
                new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK));

//        cellSupplier.addElement(nomorTanggal);
        nomorTanggal.setPaddingTop(5);
        nomorTanggal.setAlignment(Element.ALIGN_CENTER);
//        table.addCell(cellSupplier);
        document.add(nomorTanggal);


        com.itextpdf.text.Font font = new com.itextpdf.text.Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
        PdfPTable tableHeader = new PdfPTable(new float[]{4, 4, 4, 4});

        tableHeader.setPaddingTop(45);
        tableHeader.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableHeader.getDefaultCell().setFixedHeight(30);
        tableHeader.setTotalWidth(PageSize.A4.getWidth());
        tableHeader.setWidthPercentage(100);

        PdfPCell h1 = new PdfPCell(new Phrase("Tipe Mobil"));
        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
        h1.setPaddingBottom(5);

        PdfPCell h2 = new PdfPCell(new Phrase("Nama Mobil"));
        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
        h2.setPaddingBottom(5);

        PdfPCell h3 = new PdfPCell(new Phrase("Jumlah Peminjaman"));
        h3.setHorizontalAlignment(Element.ALIGN_CENTER);
        h3.setPaddingBottom(5);

        PdfPCell h4 = new PdfPCell(new Phrase("Pendapatan"));
        h4.setHorizontalAlignment(Element.ALIGN_CENTER);
        h4.setPaddingBottom(5);

//        PdfPCell h5 = new PdfPCell(new Phrase("Pendapatan"));
//        h5.setHorizontalAlignment(Element.ALIGN_CENTER);
//        h5.setPaddingBottom(5);

        tableHeader.addCell(h1);
        tableHeader.addCell(h2);
        tableHeader.addCell(h3);
        tableHeader.addCell(h4);
//        tableHeader.addCell(h5);

        for(PdfPCell cell : tableHeader.getRow(0).getCells())
        {
            cell.setBackgroundColor(BaseColor.GRAY);
        }

        document.add(tableHeader);
        PdfPTable tableData = new PdfPTable(new float[]{4, 4, 4, 4});

        tableData.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        tableData.getDefaultCell().setFixedHeight(30);
        tableData.setTotalWidth(PageSize.A4.getWidth());
        tableData.setWidthPercentage(100);
        tableData.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        for(LaporanPenyewaanMobil lap : laporanPenyewaanMobil)
        {
            tableData.addCell(lap.getTipeMobil());
            tableData.addCell(lap.getNamaMobil());
            tableData.addCell(String.valueOf(lap.getJumlahPeminjaman()));
            tableData.addCell(String.valueOf(lap.getPendapatan()));
        }

        document.add(tableData);

        document.close();
        previewPdf(pdfFile);
        Toast.makeText(this, "PDF Berhasil dibuat", Toast.LENGTH_SHORT).show();
    }

    private void previewPdf(File pdfFile)
    {
        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List<ResolveInfo> list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);

        if(list.size() > 0)
        {
            Uri uri;
            uri = FileProvider.getUriForFile(this, getPackageName() + ".provider", pdfFile);

            Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
            pdfIntent.setDataAndType(uri, "application/pdf");
            pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            pdfIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            this.grantUriPermission(getPackageName(), uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(pdfIntent);
        }
    }

    private void GetLaporan5TransaksiCustomer()
    {
        final StringRequest stringRequest = new StringRequest(GET, LaporanApi.LAPORAN_TRANSAKSICUSTOMERTBNY + calendarToUse.getTime(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Laporan5CustomerTrnsResponse newResponse =
                                gson.fromJson(response, Laporan5CustomerTrnsResponse.class);


                        try {

                        laporan5CustomerTransaksi = newResponse.getDataList();
                            if(laporan5CustomerTransaksi != null)
                            {
                                cetakLaporan5Customer();
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(ManagerMenuActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

//                        setLoading(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody =
                            new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(getApplicationContext(), errors.getString("message"),
                            Toast.LENGTH_SHORT).show();

                    if(errors.getString("message").equalsIgnoreCase("empty"))
                    {

                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void GetLaporan5TransaksiDriver()
    {
        final StringRequest stringRequest = new StringRequest(GET, LaporanApi.LAPORAN_DRIVERTRANSAKSITBNY + calendarToUse.getTime(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Laporan5DriverTrnsResponse newResponse =
                                gson.fromJson(response, Laporan5DriverTrnsResponse.class);

                        try {

                            laporan5DriverTransaksi = newResponse.getDataList();
                            if(laporan5DriverTransaksi != null)
                            {
                                cetakLaporan5Driver();
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(ManagerMenuActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

//                        setLoading(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody =
                            new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(getApplicationContext(), errors.getString("message"),
                            Toast.LENGTH_SHORT).show();

                    if(errors.getString("message").equalsIgnoreCase("empty"))
                    {

                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void GetLaporanDetailPendapatan()
    {
        final StringRequest stringRequest = new StringRequest(GET, LaporanApi.LAPORAN_DETAILPENDAPATAN + calendarToUse.getTime(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        LaporanDetailPendapatanResponse newResponse =
                                gson.fromJson(response, LaporanDetailPendapatanResponse.class);

                        try {

                            laporanDetailPendapatan = newResponse.getDataList();
                            if(laporanDetailPendapatan != null)
                            {
                                cetakLaporanDetailPendapatan();
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(ManagerMenuActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

//                        setLoading(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody =
                            new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(getApplicationContext(), errors.getString("message"),
                            Toast.LENGTH_SHORT).show();

                    if(errors.getString("message").equalsIgnoreCase("empty"))
                    {

                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void GetLaporanPerformaDriver()
    {
        final StringRequest stringRequest = new StringRequest(GET, LaporanApi.LAPORAN_PERFORMADRIVER + calendarToUse.getTime(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        LaporanPerformaDriverResponse newResponse =
                                gson.fromJson(response, LaporanPerformaDriverResponse.class);

                        try {

                            laporanPerformaDriver = newResponse.getDataList();
                            if(laporanPerformaDriver != null)
                            {
                                cetakLaporanPerformaDriver();
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(ManagerMenuActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

//                        setLoading(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody =
                            new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(getApplicationContext(), errors.getString("message"),
                            Toast.LENGTH_SHORT).show();

                    if(errors.getString("message").equalsIgnoreCase("empty"))
                    {

                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    private void GetLaporanSewaMobil()
    {
        final StringRequest stringRequest = new StringRequest(GET, LaporanApi.LAPORAN_SEWAMBL + calendarToUse.getTime(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        LaporanPenyewaanMobilResponse newResponse =
                                gson.fromJson(response, LaporanPenyewaanMobilResponse.class);

                        try {

                            laporanPenyewaanMobil = newResponse.getDataList();
                            if(laporanPenyewaanMobil != null)
                            {
                                cetakLaporanSewaMobil();
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(ManagerMenuActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

//                        setLoading(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                setLoading(false);
                try {
                    String responseBody =
                            new String(error.networkResponse.data, StandardCharsets.UTF_8);
                    JSONObject errors = new JSONObject(responseBody);

                    Toast.makeText(getApplicationContext(), errors.getString("message"),
                            Toast.LENGTH_SHORT).show();

                    if(errors.getString("message").equalsIgnoreCase("empty"))
                    {

                    }

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");

                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }
}