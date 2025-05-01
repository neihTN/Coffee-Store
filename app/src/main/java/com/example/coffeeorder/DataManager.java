package com.example.coffeeorder;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataManager {

    private static DataManager instance;

    private String userName;
    private String userPhoneNumber;
    private String userAddress;
    private String userEmail;

    private List<Coffee> coffeeList;
    private List<OrderItem> cartItems;
    private List<Coffee> rewardCoffeeList;
    private List<OrderItem> ongoingOrders;
    private List<OrderItem> historyOrders;

    private int points;
    public static final int MAX_STAMPS = 8;
    private int currentStamps;

    private Coffee currentDetailCoffee;
    private List<HistoryOrdersChangedListener> historyListeners;
    private List<UserInformationChangedListener> userInfoListeners;
    private List<LoyaltyStampChangedListener> loyaltyStampListeners;
    private List<PointChangedListener> pointListeners;
    private List<OngoingOrdersChangedListener> ongoingListeners;
    private List<CartChangedListener> cartListeners;

    private DataManager() {
        loadDefaultData();
    }

    private void loadDefaultData() {
        // Coffee objects
        Coffee americano = new Coffee("Americano", R.drawable.americano, 12, 3.00, 1400);
        Coffee capuchino = new Coffee("Capuchino", R.drawable.capuchino, 12, 3.5, 1340);
        Coffee mocha = new Coffee("Mocha", R.drawable.mocha, 13, 4, 1700);
        Coffee flatWhite = new Coffee("Flat White", R.drawable.flat_white, 14, 4.2, 1750);
        // User Information
        userName = "Anderson";
        userPhoneNumber = "+60134589525";
        userEmail = "Anderson@email.com";
        userAddress = "3 Addersion Court Chino Hills, HO56824, United State";

        // Coffee list
        coffeeList = new ArrayList<>();
        coffeeList.add(americano);
        coffeeList.add(capuchino);
        coffeeList.add(mocha);
        coffeeList.add(flatWhite);
        coffeeList.add(americano);
        coffeeList.add(capuchino);
        coffeeList.add(mocha);
        coffeeList.add(flatWhite);
        coffeeList.add(americano);
        coffeeList.add(capuchino);
        coffeeList.add(mocha);
        coffeeList.add(flatWhite);
        coffeeList.add(americano);
        coffeeList.add(capuchino);
        coffeeList.add(mocha);
        coffeeList.add(flatWhite);
        coffeeList.add(americano);
        coffeeList.add(capuchino);
        coffeeList.add(mocha);
        coffeeList.add(flatWhite);

        // Reward coffee list
        rewardCoffeeList = new ArrayList<>();
        rewardCoffeeList.add(new Coffee("Americano", R.drawable.americano, 0, 0, 1340));
        rewardCoffeeList.add(new Coffee("Capuchino", R.drawable.capuchino, 0, 0, 1400));
        rewardCoffeeList.add(new Coffee("Mocha", R.drawable.mocha, 0, 0, 1350));
        rewardCoffeeList.add(new Coffee("Flat White", R.drawable.flat_white, 0, 0, 1450));

        // History orders
        historyOrders = new ArrayList<>();
        historyOrders.add(new OrderItem(americano, 1, new Date(2024, 6, 24, 12, 30), userAddress, ""));
        historyOrders.add(new OrderItem(capuchino, 1, new Date(2024, 6, 22, 8, 30), userAddress, ""));
        historyOrders.add(new OrderItem(mocha, 2, new Date(2024, 6, 16, 10, 48), userAddress, ""));
        historyOrders.add(new OrderItem(flatWhite, 1, new Date(2024, 5, 12, 11, 25), userAddress, ""));
        historyOrders.add(new OrderItem(mocha, 2, new Date(2024, 5, 1, 10, 48), userAddress, ""));


        // On going orders
        ongoingOrders = new ArrayList<>();
        ongoingOrders.add(new OrderItem(americano, 1, new Date(), userAddress, ""));
        ongoingOrders.add(new OrderItem(capuchino, 1, new Date(), userAddress, ""));
        ongoingOrders.add(new OrderItem(mocha, 2, new Date(), userAddress, ""));
        ongoingOrders.add(new OrderItem(flatWhite, 1, new Date(), userAddress, ""));
        ongoingOrders.add(new OrderItem(mocha, 2, new Date(), userAddress, ""));

        // Cart items
        cartItems = new ArrayList<>();
        cartItems.add(new OrderItem(americano, 2, new Date(), userAddress, "single | iced | medium | few ice"));
        cartItems.add(new OrderItem(mocha, 1, new Date(), userAddress, "single | iced | small | full ice"));
        cartItems.add(new OrderItem(flatWhite, 3, new Date(), userAddress, "single | iced | big | half ice"));

        // Stamps and points
        currentStamps = 4;
        points = 2750;

        // Listeners
        historyListeners = new ArrayList<>();
        userInfoListeners = new ArrayList<>();
        loyaltyStampListeners = new ArrayList<>();
        pointListeners = new ArrayList<>();
        ongoingListeners = new ArrayList<>();
        cartListeners = new ArrayList<>();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        for (UserInformationChangedListener listener : userInfoListeners)
            listener.onChanged();
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
        for (UserInformationChangedListener listener : userInfoListeners)
            listener.onChanged();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
        for (UserInformationChangedListener listener : userInfoListeners)
            listener.onChanged();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        for (UserInformationChangedListener listener : userInfoListeners)
            listener.onChanged();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Coffee getCurrentDetailCoffee() {
        return currentDetailCoffee;
    }

    public void setCurrentDetailCoffee(Coffee currentDetailCoffee) {
        this.currentDetailCoffee = currentDetailCoffee;
    }

    public int getCurrentStamps() {
        return currentStamps;
    }

    public void setCurrentStamps(int currentStamps) {
        this.currentStamps = currentStamps;
    }

    public List<Coffee> getCoffeeList() {
        return coffeeList;
    }

    public List<Coffee> getRewardCoffeeList() {
        return rewardCoffeeList;
    }

    public List<OrderItem> getCartItems() {
        return cartItems;
    }

    public List<OrderItem> getOngoingOrders() {
        return ongoingOrders;
    }

    public List<OrderItem> getHistoryOrders() {
        return historyOrders;
    }

    public void addOrderToHistory(OrderItem order) {
        historyOrders.add(order);
        points += order.getQuantity() * order.getCoffee().getPointPerCup();
        currentStamps = Math.min(currentStamps + order.getQuantity(), 8);

        for (HistoryOrdersChangedListener listener : historyListeners)
            listener.onChanged();
        for (LoyaltyStampChangedListener listener : loyaltyStampListeners)
            listener.onChanged();
    }

    public void addOrderToCart(OrderItem order) {
        cartItems.add(order);
        for (CartChangedListener listener : cartListeners)
            listener.onChanged();
    }

    public void removeOrderFromCart(OrderItem order) {
        cartItems.remove(order);
        for (CartChangedListener listener : cartListeners)
            listener.onChanged();
    }

    public void clearCart() {
        cartItems.clear();
        for (CartChangedListener listener : cartListeners)
            listener.onChanged();
    }

    public void addOrderToOngoing(OrderItem order) {
        ongoingOrders.add(order);
        for (OngoingOrdersChangedListener listener : ongoingListeners)
            listener.onChanged();
    }

    public void updateLoyaltyStamp() {
        if (currentStamps == MAX_STAMPS) {
            currentStamps = 0;
            points += 100;
            for (LoyaltyStampChangedListener listener : loyaltyStampListeners)
                listener.onChanged();
        }
    }

    public void redeemCoffee(Context context, Coffee rewardCoffee) {
        if (points >= rewardCoffee.getPointToRedeem()) {
            points -= rewardCoffee.getPointToRedeem();
            addOrderToOngoing(new OrderItem(rewardCoffee, 1, new Date(), userAddress, "single | iced | big | full ice"));

            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();

            for (PointChangedListener listener : pointListeners)
                listener.onChanged();
        }
        else
            Toast.makeText(context, "Not enough points!", Toast.LENGTH_SHORT).show();
    }

    public interface HistoryOrdersChangedListener {
        void onChanged();
    }

    public void addHistoryListener(HistoryOrdersChangedListener listener) {
        historyListeners.add(listener);
    }

    public void removeHistoryListener(HistoryOrdersChangedListener listener) {
        historyListeners.remove(listener);
    }

    public interface UserInformationChangedListener {
        void onChanged();
    }

    public void addUserInfoListener(UserInformationChangedListener listener) {
        userInfoListeners.add(listener);
    }

    public void removeUserInfoListener(UserInformationChangedListener listener) {
        userInfoListeners.remove(listener);
    }

    public interface LoyaltyStampChangedListener {
        void onChanged();
    }

    public void addLoyaltyStampListener(LoyaltyStampChangedListener listener) {
        loyaltyStampListeners.add(listener);
    }

    public void removeLoyaltyStampListener(LoyaltyStampChangedListener listener) {
        loyaltyStampListeners.remove(listener);
    }

    public interface PointChangedListener {
        public void onChanged();
    }

    public void addPointListener(PointChangedListener listener) {
        pointListeners.add(listener);
    }

    public void removePointListener(PointChangedListener listener) {
        pointListeners.remove(listener);
    }

    public interface OngoingOrdersChangedListener {
        public void onChanged();
    }

    public void addOngoingListener(OngoingOrdersChangedListener listener) {
        ongoingListeners.add(listener);
    }

    public void removeOngoingListener(OngoingOrdersChangedListener listener) {
        ongoingListeners.remove(listener);
    }

    public interface CartChangedListener {
        public void onChanged();
    }

    public void addCartListener(CartChangedListener listener) {
        cartListeners.add(listener);
    }

    public void removeCartListener(CartChangedListener listener) {
        cartListeners.remove(listener);
    }
}

