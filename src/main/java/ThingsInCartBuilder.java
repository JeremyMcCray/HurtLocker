public class ThingsInCartBuilder {
    String name;
    String price;
    String type;
    String expiration;

    public ThingsInCartBuilder name(String name){
        this.name = name;
        return this;
    }

    public ThingsInCartBuilder price(String price){
        this.price = price;
        return this;
    }
    public ThingsInCartBuilder type(String type){
        this.type = type;
        return this;
    }
    public ThingsInCartBuilder expiration(String expiration){
        this.expiration = expiration;
        return this;
    }


    public ThingsInCart builder(){
        ThingsInCart thingsInCart = new ThingsInCart(this);

        return thingsInCart;
    }
}
