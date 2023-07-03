$("#applyFilters").click(function () {
    let authors = [];
    $('[id$="Author"]:checked').each(function () {
        let author = $(this).val();
        authors.push(author);
    });

    // Collect selected genres
    let genres = [];
    $('[id$="Genre"]:checked').each(function () {
        let genre = $(this).val();
        genres.push(genre);
    });

    // Get min and max price values
    const minPrice = $('#MinPrice').val();
    const maxPrice = $('#MaxPrice').val();

    const activeElement = document.getElementById('active');
    let activeText = '1';
    if (activeElement) {
        activeText = activeElement.textContent;
    }
    let pageNumber = parseInt(activeText);

    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");

    $(document).ajaxSend(function (e, xhr) {
        xhr.setRequestHeader(header, token);
    });
    // console.log('sending request...');
    // console.log(JSON.stringify({
    //     authors: authors,
    //     genres: genres,
    //     minPrice: minPrice,
    //     maxPrice: maxPrice,
    //     pageNumber: pageNumber,
    //     pageSize: 15
    // }));
    $.ajax({
        url: "/books",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            authors: authors,
            genres: genres,
            minPrice: minPrice,
            maxPrice: maxPrice,
            pageNumber: pageNumber,
            pageSize: 15
        }),
        success: function (data) {
            console.log("Request successful");
            $('#contentBox').innerHTML = '';
            $("#contentBox").html(data);

            $(".buy-button").click(function () {
                let bookId = $(this).attr('id');
                updateCart('/cart/add/' + bookId);
            });

            const firstElement = document.getElementById('1');
            const activeElement = document.getElementById('active');
            const lastElement = document.getElementById('last');

            firstElement.addEventListener('click', () => {
                activeElement.textContent = '1';
                $("#applyFilters").click();
            });

            lastElement.addEventListener('click', () => {
                activeElement.textContent = lastElement.textContent;
                $("#applyFilters").click();
            });

            const next = document.getElementById('next');
            const previous = document.getElementById('previous');

            next.addEventListener('click', () => {
                let num = parseInt(activeElement.textContent) + 1;
                activeElement.textContent = num.toString();
                $('#applyFilters').click();
            });

            previous.addEventListener('click', () => {
                let num = parseInt(activeElement.textContent) - 1;
                activeElement.textContent = num.toString();
                $('#applyFilters').click();
            });

        },
        error: function (xhr, status, error) {
            console.log("Request failed: " + error);
        }
    });
});