package com.example.programacion5.menudrawertabas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


/**
 * A simple {@link Fragment} subclass.
 */
public class SharkStudio extends Fragment {

//
private WebView shark;
    String url = "https://www.sharkestudio.com/";
    private ProgressBar progressBar;
    public SharkStudio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View  v =inflater.inflate(R.layout.fragment_shark_studio, container, false);

        shark = (WebView)v.findViewById(R.id.webstudio);

        shark.loadUrl(url);
        shark.getSettings().setJavaScriptEnabled(true);
        shark.setWebViewClient(new WebViewClient());
        progressBar = (ProgressBar)v.findViewById(R.id.progressbar);
        shark.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int progress)
            {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);

                progressBar.incrementProgressBy(progress);
                if(progress == 100)
                {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Transicion.getInstance().transicionFragments(getView(),getActivity());
    }

}
