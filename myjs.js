function cal() {
    var index=$('#index').val();
    var year=$('#year').val();
    var total=$('#money').val();


    var monthback=index*total/10000;

    var totalpay=monthback*year*12;

    var totalprofit=totalpay-total;

    var yearProfit=totalprofit/total;


    document.getElementById("monthlyback").innerText=monthback;
    document.getElementById("totalProfit").innerText=totalprofit;
    document.getElementById("yearlyProfit").innerText=yearProfit;
    document.getElementById("totalMoney").innerText=totalpay;
}



function reset() {

    document.getElementById("index").value="";
    document.getElementById("year").value="";
    document.getElementById("money").value="";


    document.getElementById("monthlyback").innerText="";
    document.getElementById("totalProfit").innerText="";
    document.getElementById("yearlyProfit").innerText="";
    document.getElementById("totalMoney").innerText="";


}