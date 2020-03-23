public class ThingsInCart {


    String name;
    String price;
    String type;
    String expiration;

    public ThingsInCart(ThingsInCartBuilder builder){
        this.name=builder.name;
        this.price=builder.price;
        this.type=builder.type;
        this.expiration=builder.expiration;
    }
}
