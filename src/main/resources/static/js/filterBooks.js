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

    const ulElement = document.getElementById('pages');
    let activeId = '1';
    if (ulElement) {
        const activeElement = ulElement.querySelector('.active');
        if (activeElement) {
            activeId = activeElement.id;
        }
    }
    let pageNumber = parseInt(activeId);

    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");

    $(document).ajaxSend(function (e, xhr) {
        xhr.setRequestHeader(header, token);
    });
    console.log('sending request...');
    console.log(JSON.stringify({
        authors: authors,
        genres: genres,
        minPrice: minPrice,
        maxPrice: maxPrice,
        pageNumber: pageNumber,
        pageSize: 16
    }));
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
            pageSize: 16
        }),
        success: function (data) {
            console.log("Request successful");
            $("#contentBox").html(data);
            $(".buy-button").click(function() {
                let bookId = $(this).attr('id');
                updateCart('/cart/add/'+bookId);
            });
            },
        error: function (xhr, status, error) {
            console.log("Request failed: " + error);
        }
    });
});