package com.myprog.zoop.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.myprog.zoop.AppConstant;
import com.myprog.zoop.R;
import com.myprog.zoop.ui.custom.DividerItemDecoration;
import com.myprog.zoop.util.Utility;
import com.myprog.zoop.viewmodel.ProductModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CartItemActivity  extends AppCompatActivity {
    private static final String INTENT_CART_ITEMS = "CONST_CART_ITEMS";
    private RecyclerView productsListView;
    private List<ProductModel> productList;
    private TextView totalAmout;
    private Button paymentButton;

    /**
     * Using starter pattern to avoid confusion when same activity is called from several products.
     * @param activity calling activity.
     */
    public static void start(Activity activity, ArrayList<ProductModel> list) {
        Intent startIntent = new Intent(activity, CartItemActivity.class);
        startIntent.putParcelableArrayListExtra(INTENT_CART_ITEMS, list);
        activity.startActivity(startIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_products);

        if (getIntent() != null) {
            productList = getIntent().getParcelableArrayListExtra(INTENT_CART_ITEMS);
                    // Setup recyclerView
            productsListView = (RecyclerView) findViewById(R.id.recycler_view);
            productsListView.setHasFixedSize(true);
            productsListView.setLayoutManager(new LinearLayoutManager(this));
            productsListView.addItemDecoration(new DividerItemDecoration(5));
            productsListView.setAdapter(new ProductAdapter(productList));

            totalAmout = (TextView) findViewById(R.id.total_amount_id);
            totalAmout.setText(AppConstant.CONST_AMOUNT_TO_PAY + getTotalAmount(productList));
            paymentButton = (Button) findViewById(R.id.payment_id);
            paymentButton.setOnClickListener(paymentListener);

        }
    }

    private String getTotalAmount(List<ProductModel> list) {
        double amount = 0.0;
        String currency = "EUR";
        for (ProductModel pm : list) {
            amount += Double.parseDouble(pm.price);
            currency = pm.currency;
        }

        DecimalFormat df=new DecimalFormat("0.00");
        String format = df.format(amount);

        return format + AppConstant.CONST_SPACE + currency;
    }

    View.OnClickListener paymentListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Utility.showDialog(CartItemActivity.this, AppConstant.CONST_FEATURE_PAYMENT_COMING_SOON);
        }
    };
}
