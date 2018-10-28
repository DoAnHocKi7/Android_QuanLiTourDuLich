package com.example.tranthibay.ql_tourdulich.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.tranthibay.ql_tourdulich.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private List<Suggestion> mSuggestions =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.search_view_layout);
        initData();

        final FloatingSearchView searchView= (FloatingSearchView) findViewById(R.id.floating_search_view);

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                if (!oldQuery.equals("") && newQuery.equals("")) {
                    searchView.clearSuggestions();
                } else {
                    searchView.showProgress();
                    searchView.swapSuggestions(getSuggestion(newQuery));
                    searchView.hideProgress();
                }
            }
        });
        searchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                searchView.showProgress();
                searchView.swapSuggestions(getSuggestion(searchView.getQuery()));
                searchView.hideProgress();
            }

            @Override
            public void onFocusCleared() {

            }
        });
        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                Suggestion suggestion= (Suggestion) searchSuggestion;
            }

            @Override
            public void onSearchAction(String currentQuery) {

            }
        });
    }

    private void initData(){
        mSuggestions.add(new Suggestion("Ha Noi"));
        mSuggestions.add(new Suggestion("Ha nam"));
        mSuggestions.add(new Suggestion("Da nang"));
        mSuggestions.add(new Suggestion("Dong nai"));
        mSuggestions.add(new Suggestion("Phú Tho"));
        mSuggestions.add(new Suggestion("Quang ngai"));
        mSuggestions.add(new Suggestion("Thanh hoa"));
        mSuggestions.add(new Suggestion("Hue"));
    }

    private List<Suggestion> getSuggestion(String query){
        List<Suggestion> suggestions=new ArrayList<>();
        for(Suggestion suggestion:mSuggestions){
            if(suggestion.getmName().toLowerCase().contains(query.toLowerCase())){
                suggestions.add(suggestion);
            }
        }
        return suggestions;
    }
}
