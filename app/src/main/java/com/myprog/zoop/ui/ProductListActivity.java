package com.myprog.zoop.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.myprog.zoop.AppConstant;
import com.myprog.zoop.R;
import com.myprog.zoop.ui.ProductAdapter.ProductCardClickListener;
import com.myprog.zoop.ui.ProductController.ProductResultListener;
import com.myprog.zoop.ui.custom.DividerItemDecoration;
import com.myprog.zoop.util.Utility;
import com.myprog.zoop.viewmodel.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity implements ProductCardClickListener, ProductResultListener {

    private RecyclerView productsListView;
    private List<ProductModel> productList;
    private ArrayList<ProductModel> cartList;
    private ProgressBar spinner;

    /**
     * Using starter pattern to avoid confusion when same activity is called from several products.
     * @param activity calling activity.
     */
    public static void start(Activity activity) {
        Intent startIntent = new Intent(activity, ProductListActivity.class);
        activity.startActivity(startIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zooplus_products);

        cartList = new ArrayList<>();

        // Setup recyclerView
        productsListView = (RecyclerView) findViewById(R.id.recycler_view);
        productsListView.setHasFixedSize(true);
        productsListView.setLayoutManager(new LinearLayoutManager(this));
        productsListView.addItemDecoration(new DividerItemDecoration(5));

        spinner = (ProgressBar)findViewById(R.id.progress_bar);


        new ProductController(this).stubRequestProductService();
    }

    @Override
    public void onProductCardClick(int position) {
        Utility.showDialog(ProductListActivity.this, AppConstant.CONST_FEATURE_DETAILS_COMING_SOON);
    }

    @Override
    public void addToCart(int position) {
        cartList.add(productList.get(position));
    }

    @Override
    public void provideProductsResult(List<ProductModel> list) {
        productList = list;
        if (productList != null) {
            productsListView.setAdapter(new ProductAdapter(this, productList));
        } else {
            Utility.showDialog(ProductListActivity.this, AppConstant.NO_DATA_AVAILABLE);
        }
        spinner.setVisibility(View.GONE);
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
            if (cartList.isEmpty()) {
                Utility.showDialog(ProductListActivity.this, AppConstant.CONST_EMPTY_CART);
            } else {
                CartItemActivity.start(ProductListActivity.this, cartList);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
