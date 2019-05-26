  document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems);
  });

//    document.addEventListener('DOMContentLoaded', function() {
//      var elems = document.querySelectorAll('.timepicker');
//      var instances = M.Timepicker.init(elems);
//    });
  document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems);
  });