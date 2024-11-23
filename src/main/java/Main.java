import Model.GetCurrency;
import Model.libs.CurrencyObject;
import View.Inv;

public class Main {
    public static void main(String[] args) {

        new Inv();
        GetCurrency currency = new GetCurrency();
        CurrencyObject priceDollar = currency.getCurrency();

        System.out.println(priceDollar.bcv());
        System.out.println(priceDollar.enParalelo());




    }
}