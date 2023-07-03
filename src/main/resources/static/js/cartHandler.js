function createCartTable(cart) {
    // Create the table
    let table = document.createElement("table");
    table.classList.add("table");
    table.classList.add("text-white");

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
    cart.forEach(function(cartItem) {
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
        quantityCell.textContent = cartItem.quantity;
        row.appendChild(quantityCell);

        // Add the row to the table body
        tbody.appendChild(row);
    });
    table.appendChild(tbody);
    let tfoot = document.createElement("tfoot");
    let footerRow = document.createElement("tr");
    let totalCell = document.createElement("td");
    totalCell.setAttribute("colspan", "4");
    totalCell.classList.add("text-end");
    totalCell.textContent = "Total: $" + calculateTotal(cart).toFixed(2); // Assuming calculateTotal() is a function to calculate the total
    footerRow.appendChild(totalCell);
    tfoot.appendChild(footerRow);
    table.appendChild(tfoot);
    return table;
}
function calculateTotal(cart) {
    let total = 0;
    cart.forEach(function (cartItem) {
        total += cartItem.price * cartItem.quantity;
    });
    return total;
}
function updateCart(url) {
    $.ajax({
        url: url,
        method: "GET",
        contentType: "application/json",
        success: function (data) {
            let cart = Object.entries(data.cart).map(([key, value]) => {
                // Extracting the id, title, and price from the key string
                let itemString = key.substring(key.indexOf('(') + 1, key.indexOf(')'));
                let [id, title, price] = itemString.split(', ');

                return {
                    id: parseInt(id.split('=')[1]),
                    title: title.split('=')[1],
                    price: parseFloat(price.split('=')[1]),
                    quantity: value
                };
            });
            let table = createCartTable(cart);
            $("#cartTableContainer").html(table);

        },
        error: function (xhr, status, error) {
            console.log("Request failed: " + error);
        }
    });
}

