package br.com.doghero.dhproject.myHeroes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.doghero.dhproject.util.NumberUtil;
import br.com.doghero.dhproject.R;
import br.com.doghero.dhproject.util.ImageHelper;
import br.com.doghero.dhproject.model.Hero;
import br.com.doghero.dhproject.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyHeroesAdapter extends RecyclerView.Adapter<MyHeroesAdapter.ViewHolder> {

    private List<Hero> mHeroList;

    private Context mContext;

    private MyHeroesListView mView;


    public MyHeroesAdapter(MyHeroesListView view, List<Hero> heroList) {
        this.mHeroList = heroList;
        this.mView = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.mContext = viewGroup.getContext();
        View rootView = LayoutInflater.from(this.mContext).inflate(R.layout.hero_list_item, viewGroup, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Hero hero = mHeroList.get(i);
        User user;

        if (hero != null && hero.getUser() != null) {
            holder.hero = hero;
            user = hero.getUser();

            holder.name.setText(user.getFirstName());
            ImageHelper.loadImage(this.mContext, user.getImageUrl(),
                    android.R.drawable.ic_dialog_alert, holder.imgHeroAvatar);
            holder.price.setText(NumberUtil.formatNumberToCurrencyText(hero.getPrice()));
            holder.txtNeighborhood.setText(hero.getAddresssNeighborhood());

            if (hero.isSuperhero()) {
                holder.imgSuperHero.setVisibility(View.VISIBLE);
            } else {
                holder.imgSuperHero.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.mHeroList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        Hero hero;
        View mItemView;

        @BindView(R.id.txtName)
        TextView name;

        @BindView(R.id.txtNeighborhood)
        TextView txtNeighborhood;

        @BindView(R.id.imgHeroAvatar)
        ImageView imgHeroAvatar;

        @BindView(R.id.price)
        TextView price;

        @BindView(R.id.imgSuperHero)
        ImageView imgSuperHero;

        public ViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.btnChat)
        public void onChatButtonClick() {
            mView.onChatButtonClick(this.hero);
        }

        @OnClick(R.id.btnBook)
        public void onBookButtonClick() {
            mView.onBookButtonClick(this.hero);
        }

    }
}
