var quantity = document.getElementsByClassName('quantity');
for (var i = 0; i < quantity.length; i++) {

    var input = quantity[i].addEventListener('change', function() {
        updateTotal();
        console.log(1);
    });

}

function updateTotal() {
    var total = 0;
    var quanElement = document.getElementsByClassName("quantity");
    var priceElement = document.getElementsByClassName("price");

    for (var i = 0; i < quanElement.length; i++) {

        var quanValue = quanElement[i].value;

        if (isNaN(quanValue) || quanValue <= 0) {
            quanElement[i].value = 1;
        }
        var priceValue = priceElement[i].innerHTML;
        var pricex = priceValue.split("$");
        var priceFloat = parseFloat(pricex[1]);

        total = total + (quanValue * priceFloat);

        document.getElementById('cart-total').innerHTML = '$' + total.toPrecision(5);
    }
    console.log(total);

}