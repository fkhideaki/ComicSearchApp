package com.example.hide.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private enum ShopType
    {
        M,A,T,G,
    }

    private class SearchCmd implements View.OnClickListener
    {
        ShopType st;

        public SearchCmd(ShopType st_)
        {
            st = st_;
        }

        @Override
        public void onClick(View v) {
            onSearch(st);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Button button = (Button)this.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtn1Clicked();
            }
        });
        */

        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnBackClicked();
            }
        });

        Button btnTop = (Button) findViewById(R.id.buttonTop);
        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnTopClicked();
            }
        });

        Button btnSearchM = (Button) findViewById(R.id.buttonSearchM);
        Button btnSearchA = (Button) findViewById(R.id.buttonSearchA);
        Button btnSearchT = (Button) findViewById(R.id.buttonSearchT);
        Button btnSearchG = (Button) findViewById(R.id.buttonSearchG);
        btnSearchM.setOnClickListener(new SearchCmd(ShopType.M));
        btnSearchA.setOnClickListener(new SearchCmd(ShopType.A));
        btnSearchT.setOnClickListener(new SearchCmd(ShopType.T));
        btnSearchG.setOnClickListener(new SearchCmd(ShopType.G));

        setupWebview();
    }

    private void onSearch(ShopType st) {
        WebView wv = (WebView) findViewById(R.id.webView);
        EditText t = (EditText) findViewById(R.id.editSearch);
        SpannableStringBuilder sb = (SpannableStringBuilder)t.getText();
        String w = sb.toString();
        String url = makeURL(w, st);
        wv.loadUrl(url);
    }

    private String makeURL(String t, ShopType st) {
        switch (st)
        {
            case M: return makeUrlM(t);
            case T: return makeUrlT(t);
            case A: return makeUrlA(t);
            case G: return makeUrlG(t);
            default: return null;
        }
    }

    private String makeUrlM(String w) {
        int mode = 2;
        String s = "https://www.melonbooks.co.jp/search/search.php";
        s += "?mode=search";
        s += "&search_disp=";
        s += "&category_id=0";
        s += "&text_type=";
        s += "&text_type=all";
        s += "&name=" + w;
        if (mode == 1)
            s += "&category_ids%5B%5D=1";
        else if (mode == 2)
            s += "&category_ids%5B%5D=4";

        return s;
    }

    private String makeUrlT(String w) {
        int mode = 2;
        String s = "https://ec.toranoana.shop/tora/ec/app/catalog/list/";
        s += "?searchDisplay=0";
        s += "&searchBackorderFlg=1";
        s += "&searchWord=" + w;
        if (mode == 1)
            s += "&commodity_kind_name=同人誌";
        else if (mode == 2)
            s += "&commodity_kind_name=書籍";
        return s;
    }

    private String makeUrlA(String w) {
        String s = "https://www.animate-onlineshop.jp/products/list.php";
        s += "?mode=search";
        s += "&smt=" + w;
        return s;
    }

    private String makeUrlG(String w) {
        String s = "https://www.gamers.co.jp/products/list.php";
        s += "?mode=search";
        s += "&smt=" + w;
        return s;
    }

    private void onBtnBackClicked() {
        WebView wv = (WebView) findViewById(R.id.webView);
        wv.goBack();
    }

    private void onBtnTopClicked() {
    }

    private void setupWebview() {
        WebView wv = (WebView) findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient());
        WebSettings s = wv.getSettings();
        s.setLoadWithOverviewMode(true);
        s.setUseWideViewPort(true);
        s.setJavaScriptEnabled(true);
    }

    private void showMsgBoxTest() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("title")
                .setMessage("message")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // OK button pressed
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
