function createCartTable(cart) {
    // Create the table
    let table = document.createElement("table");
    table.classList.add("table");

    // Create the table header
    let thead = document.createElement("thead");
    let headerRow = document.createElement("tr");
    let headers = ["ID", "Title", "Price", "Quantity"];
    headers.forEach(function(headerText) {
        let th = document.createElement("th");
        th.textContent = headerText;
        headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    table.appendChild(thead);

    // Create the table body
    let tbody = document.createElement("tbody");
    cart.forEach(function(cartItem, quantity) {
        let row = document.createElement("tr");

        // Create the table cells
        let idCell = document.createElement("td");
        idCell.textContent = cartItem.id;
        row.appendChild(idCell);

        let titleCell = document.createElement("td");
        titleCell.textContent = cartItem.title;
        row.appendChild(titleCell);

        let priceCell = document.createElement("td");
        priceCell.textContent = cartItem.price.toFixed(2); // Assuming price is a decimal value
        row.appendChild(priceCell);

        let quantityCell = document.createElement("td");
        quantityCell.textContent = quantity;
        row.appendChild(quantityCell);

        // Add the row to the table body
        tbody.appendChild(row);
    });
    table.appendChild(tbody);

    return table;
}

$("#cartButton").click(function (){
    $.ajax({
        url: "/cart",
        method: "GET",
        contentType: "application/json",
        success: function (data) {
            console.log("Request successful");
            console.log(data.cart);
            let cartJson = data;
            let cart = cartJson.cart.map(function(item) {
                return {
                    id: item.cartItem.id,
                    title: item.cartItem.title,
                    price: item.cartItem.price
                };
            });
            let table = createCartTable(cart);
            let container = $("#cartTableContainer");
            container.appendChild(table);
        },
        error: function (xhr, status, error) {
            console.log("Request failed: " + error);
        }
    });
});

