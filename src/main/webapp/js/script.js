function add(pid, pname, price) {
    let card = localStorage.getItem("card");
    if (card == null) {
        // no card so add the card
        let products = [];
        let product = {productId: pid, productName: pname, productQuantity: 1, productPrice: price};
        products.push(product);
        localStorage.setItem("card", JSON.stringify(products));
        console.log("Product is add first time");


    } else {
        // card  alerdy is add
        let pcard = JSON.parse(card);
        let oldProduct = pcard.find((item) => item.productId == pid);
        if (oldProduct) {
            // we have increase the quantity
            oldProduct.productQuantity = oldProduct.productQuantity + 1;
            pcard.map((item) => {
                if (item.productId == oldProduct.productId) {
                    item.productQuantity = oldProduct.productQuantity;
                }
            })
            localStorage.setItem("card", JSON.stringify(pcard));
            console.log("Product is add quantity increased");

        } else {
            // add to product
            let product = {productId: pid, productName: pname, productQuantity: 1, productPrice: price};
            pcard.push(product);
            localStorage.setItem("card", JSON.stringify(pcard));
            console.log("Product is added");
        }

        updateCart();
    }
}

// update cart
function updateCart() {
    let cartString = localStorage.getItem("cart");
    let cart = JSON.parse(cartString);
    if (cart == null || cart.length == 0)
    {
        console.log("Cart is Empty");
        $(".cart-items").html("( 0 )");
        $(".cart-body").html("<h3>cart dose not have any items </h3>");
        $(".checkout-btn").prop('disabled', true);
    } else {
        // update cart to something
        console.log("cart");
        $(".cart-items").html(` ( ${cart.length} ) `);
        $(".checkout-btn").prop('disabled', false);

    }
}