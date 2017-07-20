package com.shellcore.android.shellforkrecipes.recipelist.adapters;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.SendButton;
import com.facebook.share.widget.ShareButton;
import com.shellcore.android.shellforkrecipes.R;
import com.shellcore.android.shellforkrecipes.db.entities.Recipe;
import com.shellcore.android.shellforkrecipes.libs.base.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cesar on 20/07/2017.
 */
public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    // Variables
    private List<Recipe> list;
    private OnItemClickListener clickListener;

    // Servicios
    private ImageLoader imageLoader;


    public RecipeListAdapter(ImageLoader imageLoader, List<Recipe> list, OnItemClickListener clickListener) {
        this.imageLoader = imageLoader;
        this.list = list;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_stored_recipes, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe currentRecipe = list.get(position);

        imageLoader.load(holder.imgRecipe, currentRecipe.getImageUrl());
        holder.txtRecipeName.setText(currentRecipe.getTitle());
        holder.btnFavorite.setTag(currentRecipe.isFavorite());

        if (currentRecipe.isFavorite()) {
            holder.btnFavorite.setImageResource(R.drawable.ic_star_full);
            holder.btnFavorite.setColorFilter(Color.YELLOW);
        } else {
            holder.btnFavorite.setImageResource(R.drawable.ic_star_empty);
            holder.btnFavorite.setColorFilter(Color.GRAY);
        }

        holder.setOnClickListener(currentRecipe, clickListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.list = recipes;
        notifyDataSetChanged();
    }

    public void removeRecipe(Recipe recipe) {
        list.remove(recipe);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        // Variables
        private View view;

        // Componentes
        @BindView(R.id.img_recipe)
        ImageView imgRecipe;
        @BindView(R.id.txt_recipe_name)
        TextView txtRecipeName;
        @BindView(R.id.btn_favorite)
        ImageButton btnFavorite;
        @BindView(R.id.btn_delete)
        ImageButton btnDelete;
        @BindView(R.id.btn_share)
        ShareButton btnShare;
        @BindView(R.id.btn_send)
        SendButton btnSend;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public void setOnClickListener(final Recipe obj, final OnItemClickListener clickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(obj);
                }
            });

            btnFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onFavClick(obj);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onDeleteClick(obj);
                }
            });

            ShareLinkContent content = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(obj.getSourceUrl()))
                    .build();

            btnShare.setShareContent(content);
            btnSend.setShareContent(content);
        }
    }
}
