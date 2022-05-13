$(function(){
	console.log("loded")
     function readURL(input){
        if(input.files && input.files[0]){
            var reader = new FileReader();
            reader.onload = function(e){
                $('#imgPreview').attr('src', e.target.result).width(100).height(100);
            }
            reader.readAsDataURL(input.files[0])
        }
    }
    $('#productImage').change(function(){
        readURL(this);
    });
    $(".custom-file-input").on("change", function() {
        var fileName = $(this).val().split("\\").pop();
        $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
    });
   
   //product detais counter
  const minus = $('.dec');
  const plus = $('.inc');
  const input = $('.qty');
  const product_price=parseInt($('#product_price').val())
  var price=$("#price")
  console.log(product_price);
  
  minus.click(function(e) {
    e.preventDefault();
    var value = input.val();
    if (value > 1) {
      value--;
      price.text(product_price*value)
    }
    input.val(value);
  });
  
  plus.click(function(e) {
    e.preventDefault();
    var value = input.val();
    value++;
   price.text(product_price*value)
    input.val(value);
  })
   
   
   
    
    //cart counter button
var buttonPlus = $(".plus");
var buttonMinus = $(".minus");

var incrementPlus = buttonPlus.click(function() {
  var $n = $(this)
    .parent(".button-container")
  var $productId=$n.find(".productid").val();  
  var $qty=$n.find(".qty").val();
  var $newQuantity=parseInt($qty)+1;
  
   save_to_db($productId,$newQuantity,$n)

});

var incrementMinus = buttonMinus.click(function() {
  var $n = $(this)
    .parent(".button-container")
  var $productId=$n.find(".productid").val();
  var $qty=$n.find(".qty").val();
  
  if ($qty > 1) {
    var $newQuantity=parseInt($qty)-1;
    save_to_db($productId,$newQuantity,$n)
  }
  
});

function save_to_db(cart_id, new_quantity,inputField) {
	
	console.log(cart_id +" "+new_quantity)

   $.ajax({
 	 type: "POST",
 	 url: "/updateCart",
 	 data: "product_id="+cart_id+"&quantity="+new_quantity,
 	 cache: false,
  	success: function(res){
		console.log(res)
		$(".cart").empty();
		$(".cart").append(res)
    	//inputField.find(".qty").val(new_quantity);
    	
  	}, error: function(){
   		 alert("failed");
 	 }
	});
	
};


    
    });