var currentPage = 1;

function switchPage(page, numPerPage, installationSize) {
    console.log("old page" + currentPage);
    currentPage = currentPage + page;
    console.log("new page" + currentPage);
    var first = (currentPage - 1) * numPerPage;
    var last = (currentPage) * numPerPage;
    console.log(installationSize);
    for (i = 0; i < 20; i++) {
        if (i >= first && i < last) {
            console.log("showing " + i);
            console.log("isvisible " + $('#articleresult' + i).is(":visible"));
            console.log("isvisible " + $('#articleresult' + i).attr('id'));
            $('#articleresult' + i).show();
        } else {
            console.log("hiding " + i);
            console.log("isvisible " + $('#articleresult' + i).is(":visible"));
            console.log("isvisible " + $('#articleresult' + i).attr('id'));
            $('#articleresult' + i).hide();
        }
    }
}
;
