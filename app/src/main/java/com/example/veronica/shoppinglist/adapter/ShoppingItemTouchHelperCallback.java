package com.example.veronica.shoppinglist.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Veronica on 11/3/16.
 */

public class ShoppingItemTouchHelperCallback
        extends ItemTouchHelper.Callback {
    /*
    Will tell if something has been swiped OR if something has been moved with drag and drop.
    Must then have access to adapter if things have been moved/changed to refresh the list.
     */

    private ShoppingListTouchHelperAdapter shoppingListTouchHelperAdapter;

    //constructor
    public ShoppingItemTouchHelperCallback(ShoppingListTouchHelperAdapter shoppingListTouchHelperAdapter) {
        this.shoppingListTouchHelperAdapter = shoppingListTouchHelperAdapter;
    }

    //Specifies can only drag flags up and down
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        shoppingListTouchHelperAdapter.onItemMove(viewHolder.getAdapterPosition(),
                target.getAdapterPosition());
        return true;
    }

    /*
    Remove a single item from the list when it is swipped left, else edit when swipped right
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        shoppingListTouchHelperAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
