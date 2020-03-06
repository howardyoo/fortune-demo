
function loadOne() {
    console.log("Getting random fortune");
    $.ajax({
        url: "/fortune",
        success: function (data) {
           var container = $('#fortune');
           console.log("Adding Fortune: " + data);
           if(data) {
             container.append("<p>&ldquo;" + data.text + "&rdquo;</p>");
           } else {
             container.append("<p>&ldquo;If you want the rainbow, you have to tolerate the rain.&rdquo;</p>");
           }
         },
         error: function () {
           var container = $('#fortune');
           container.append("<p>&ldquo;Don't worry about money.&rdquo;</p>");
        }
    });

  // getting wf info
  getWfInfo();
}

function loadAll() {
    console.log("Getting all fortunes");

    $.ajax({
        url: "/fortunes"
    }).then(function(data) {
        var container = $('#fortune');
        console.log("Adding Fortunes: " + data);
        container.empty();
        if(data) {
            $.each(data, function(index, value) {
                console.log("Adding fortune " + index);
                container.append("<p>&ldquo;" + value.text + "&rdquo;</p>");
            });
        } else {
            container.append("<p>-- Empty --</p>");
        }
    });

    // getting wf info
    getWfInfo();
}

// retrieve wavefront info (if exists)
function getWfInfo() {
  console.log("Getting wavefront info");
  $.ajax({
    url: "/wf-info",
    success: function (data) {
      var container = $('#wavefront-info');
      // if one time link exists
      var clicked = data["oneTimeClicked"];
      console.log("clicked: " + clicked);
      var buff = "";
      if (clicked == false) {
        // print out greeting and one time link
        var link = data["oneTimeLink"];
        if (link) {
          buff += "Your Wavefront Monitoring is ready. ";
          buff +=
              "<a href=\"" + link
              + "\" target=\"_blank\" onclick='enableWavefront()'>Please activate your Wavefront Account</a> now!";
        }
      } else {
        // get the url
        var link = data["url"];
        if(link) {
          buff += "Monitor your application using Wavefront at ";
          buff += "<a href=\"" + link + "\" target=\"_blank\">" + link + "</a>";
          buff += " | ";
          buff += "<a href=\"wavefront.html\" target=\"_blank\">Access Wavefront Charts</a>";
        }
      }
      container.html(buff);
    },
    error: function () {
    }
  });
}

function enableWavefront() {
  console.log("Enabling wavefront");
  $.ajax({
    url: "/wf-enable",
    success: function (data) {
      console.log("enabling wavefront: " + data);
      // refresh wf info
      getWfInfo();
    },
    error: function() {
      console.log("WF enablement returned error.");
    }
  });
}

function handle(event) {
    if (event.keyCode == 13) {
        event.preventDefault();
        var dataObject = new Object();
        dataObject.text = input_fortune.value;
        console.log("Data: " + dataObject);
        $.ajax({
            url: "/fortune",
            method: "PUT",
            data: JSON.stringify(dataObject),
            dataType: 'json',
            contentType:"application/json; charset=utf-8",
            success: function () {
                location.reload();
            }
        });
    }
};
