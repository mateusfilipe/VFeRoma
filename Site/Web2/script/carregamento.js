
    var i = setInterval(function () {
    
        clearInterval(i);
      
        // O código desejado é apenas isto:
        document.getElementById("preloader").style.display = "none";
        document.getElementById("conteudo").style.display = "inline";
    
    }, 1000);
