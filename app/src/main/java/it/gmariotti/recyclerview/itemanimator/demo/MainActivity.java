/*
 * ******************************************************************************
 *   Copyright (c) 2014 Gabriele Mariotti.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *  *****************************************************************************
 */
package it.gmariotti.recyclerview.itemanimator.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import it.gmariotti.recyclerview.itemanimator.SlideInOutBottomItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutRightItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutTopItemAnimator;


public class MainActivity extends Activity {

    RecyclerView mRecyclerView;
    SimpleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        setupSpinner();

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        mAdapter = new SimpleAdapter(this,sCheeseStrings);

        mRecyclerView.setAdapter(mAdapter);
    }


    private void setupSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.animators, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        mRecyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(mRecyclerView));
                        break;
                    case 1:
                        mRecyclerView.setItemAnimator(new SlideInOutRightItemAnimator(mRecyclerView));
                        break;
                    case 2:
                        mRecyclerView.setItemAnimator(new SlideInOutTopItemAnimator(mRecyclerView));
                        break;
                    case 3:
                        mRecyclerView.setItemAnimator(new SlideInOutBottomItemAnimator(mRecyclerView));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.recycler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add) {
            mAdapter.add("New String",SimpleAdapter.LAST_POSITION);
            return true;
        }
        if (id == R.id.action_remove) {
            mAdapter.remove(SimpleAdapter.LAST_POSITION);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static final String[] sCheeseStrings = {
            "Abbaye de Belloc" , "Abbaye du Mont des Cats" };
}
