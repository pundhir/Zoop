package com.myprog.zoop.ui;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myprog.zoop.AppConstant;
import com.myprog.zoop.R;
import com.myprog.zoop.util.Utility;
import com.myprog.zoop.viewmodel.ProductModel;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductModel> items;
    private ProductCardClickListener listener;
    private boolean isShowCart;

    public ProductAdapter(List<ProductModel> list) {
        super();
        items = list;
        isShowCart = false;
    }

    public ProductAdapter(ProductCardClickListener productCardClickListener, List<ProductModel> list) {
        super();
        items = list;
        listener = productCardClickListener;
        isShowCart = true;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View holderView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_view, viewGroup, false);
        return new ProductViewHolder(holderView, new ItemClickListener() {
            @Override
            public void onClick(View view, ProductViewHolder productViewHolder) {
                switch (view.getId()) {
                    case R.id.cv_product_id:
                        if (isShowCart) listener.onProductCardClick(productViewHolder.itemPosition);
                        break;
                    case R.id.tv_product_add:
                        if (isShowCart) listener.addToCart(productViewHolder.itemPosition);
                }

            }
        });
    }

    @Override
    public void onBindViewHolder(ProductViewHolder viewHolder, int i) {
        ProductModel pm = items.get(i);
        viewHolder.itemPosition = i;
        viewHolder.productIcon.setBackgroundResource(Utility.getImageResId(Integer.parseInt(pm.id)));
        viewHolder.productName.setText(pm.name);
        viewHolder.price.setText(AppConstant.CONST_PRICE + pm.price + AppConstant.CONST_SPACE + pm.currency);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * View holder class.
     */
    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView productIcon;
        private ImageView cart;
        private TextView productName;
        private TextView price;
        private ItemClickListener clickListener;

        private int itemPosition;

        private ProductViewHolder(View itemView, ItemClickListener listener) {
            super(itemView);
            clickListener = listener;
            ((CardView) itemView.findViewById(R.id.cv_product_id)).setOnClickListener(this);
            productIcon = (ImageView) itemView.findViewById(R.id.iv_product_icon);
            productName = (TextView) itemView.findViewById(R.id.tv_product_name);
            cart = (ImageView) itemView.findViewById(R.id.tv_product_add);
            price = (TextView) itemView.findViewById(R.id.tv_product_price);

            if (!isShowCart) {
                cart.setVisibility(View.GONE);
            }
            cart.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, this);
        }
    }

    public interface ItemClickListener {
        void onClick(View view, ProductViewHolder productViewHolder);
    }

    public interface ProductCardClickListener {
        void onProductCardClick(int position);

        void addToCart(int position);
    }
}
