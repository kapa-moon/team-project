
var modal = document.getElementById("myModal");

var btn = document.getElementById("myBtn");

var span = document.getElementsByClassName("close")[0];


btn.onclick = function() {
  modal.style.display = "block";
}

//Close modal by clicking X
span.onclick = function() {
  modal.style.display = "none";
}

//Close modal by clicking anywhere
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}


const open = document.getElementById('open');
const close = document.getElementById('close');
const modal_container = document.getElementById('modal-container');

document.getElementById('open').addEventListener('click', ()=> {
  modal_container.classList.add('show');
})

document.getElementById('close').addEventListener('click', ()=> {
  modal_container.classList.remove('show');
})


$(document).ready(function(){
  $('.search-select select').selectpicker();
})