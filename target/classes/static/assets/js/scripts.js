
var project = {};
var swalInit = swal.mixin({
    buttonsStyling: false,
    confirmButtonClass: 'btn btn-primary',
    cancelButtonClass: 'btn btn-light'
});
//GET PROJECT TYPE INTO SELECTION BOX....................................
$('document').ready(function () {
    project.viewProject();
    project.statistics();
    project.getUser();
    project.viewArtisteList();
    project.viewSongList();
    project.viewBookingList();
    project.bookingdata();
    formatAMPM();

});
//DATE AND TIME DISPLAY 
$("#timee").html(formatAMPM());
function formatAMPM() {
    var d = new Date(),
            seconds = d.getSeconds().toString().length == 1 ? '0' + d.getSeconds() : d.getSeconds(),
            minutes = d.getMinutes().toString().length == 1 ? '0' + d.getMinutes() : d.getMinutes(),
            hours = d.getHours().toString().length == 1 ? '0' + d.getHours() : d.getHours(),
            ampm = d.getHours() >= 12 ? 'pm' : 'am',
            months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
            days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
    return days[d.getDay()] + ' ' + months[d.getMonth()] + ' ' + d.getDate() + ' ' + d.getFullYear() + ' ' + hours + ':' + minutes + ':' + seconds + ampm;
}
setInterval(formatAMPM, 1000);

//Artiste input field
var i = 1;
$('.addArtisteInput').click(function () {
    i++;
    $('.artiste_field').append('<tr id="row' + i + '"><td><input type="text" name="artistename"  class="form-control " /></td><td><button type="button" name="remove" id="' + i + '" class="btn btn-warning btn_remove btn-sm">X</button></td></tr>');
});
////Songlist input field
var i = 1;
$('.songlistInput').click(function () {
    i++;
    $('.songlist_field').append('<tr id="row' + i + '"><td><input type="text" name="songtitle"  class="form-control " /></td><td><button type="button" name="remove" id="' + i + '" class="btn btn-warning btn_remove btn-sm">X</button></td></tr>');
});

//Producers input field
var i = 1;
$('.producersInput').click(function () {
    i++;
    $('.producers_field').append('<tr id="row' + i + '"><td><input type="text" name="producer"  class="form-control " /></td><td><button type="button" name="remove" id="' + i + '" class="btn btn-warning btn_remove btn-sm">X</button></td></tr>');
});

////Writers input field
var i = 1;
$('.writersInput').click(function () {
    i++;
    $('.writers_field').append('<tr id="row' + i + '"><td><input type="text" name="writer"  class="form-control " /></td><td><button type="button" name="remove" id="' + i + '" class="btn btn-warning btn_remove btn-sm">X</button></td></tr>');
});

////Engineers input field
var i = 1;
$('.engineersInput').click(function () {
    i++;
    $('.engineers_field').append('<tr id="row' + i + '"><td><input type="text" name="engineer" class="form-control " /></td><td><button type="button" name="remove" id="' + i + '" class="btn btn-warning btn_remove btn-sm">X</button></td></tr>');
});
////Remove input field
$(document).on('click', '.btn_remove', function () {
    var button_id = $(this).attr("id");
    $('#row' + button_id + '').remove();
});


//GET PROJECT TYPE INTO SELECTION BOX....................................        
var _fetchProjectType = function (callback) {
    var req = $.ajax({
        url: "/projecttypeList",
        type: "GET",
        dataType: 'json'
    });
    req.done(function (data) {
        callback(data);
    }).fail(function (jqXHR, textStatus) {
    });
};
_fetchProjectType(function (data) {
    $.each(data, function (i, item) {
        $('.projecttypead').append($('<option>', {
            value: [item],
            text: [item]
        }));
    });
});
//GET ACTIVITY TYPE INTO SELECTION BOX....................................    
var _fetchActivityType = function (callback) {
    var req = $.ajax({
        url: "/activitytypeList",
        type: "GET",
        dataType: 'json'
    });
    req.done(function (data) {
        callback(data);
    }).fail(function (jqXHR, textStatus) {
    });
};
_fetchActivityType(function (data) {
    $.each(data, function (i, item) {
        $('.activitytypead').append($('<option>', {
            value: [item],
            text: [item]
        }));
    });
});
//GET GENRE INTO SELECTION BOX....................................
var _fetchGenre = function (callback) {
    var req = $.ajax({
        url: "/genreList",
        type: "GET",
        dataType: 'json'
    });
    req.done(function (data) {
        callback(data);
    }).fail(function (jqXHR, textStatus) {
    });
};
_fetchGenre(function (data) {
    $.each(data, function (i, item) {
        $('.genread').append($('<option>', {
            value: [item],
            text: [item]
        }));
    });
});
//GET COMBINATION INTO SELECTION BOX....................................
var _fetchCombination = function (callback) {
    var req = $.ajax({
        url: "/combination",
        type: "GET",
        dataType: 'json'
    });
    req.done(function (data) {
        callback(data);
    }).fail(function (jqXHR, textStatus) {
    });
};
_fetchCombination(function (data) {
    $.each(data, function (i, item) {
        $('.combinationad').append($('<option>', {
            value: [item],
            text: [item]
        }));
    });
});
//ADD PROJECT................................................
$('#main').on('submit', function (e) {
    e.preventDefault();
    event.preventDefault();
    var data = {};
    $.each(this, function (i, v) {
        var input = $(v);
        data[input.attr("name")] = input.val();
    });
    var data = JSON.stringify(data);
    $.ajax({
        url: "/project",
        type: "POST",
        data: data,
        contentType: 'application/json',
        success: function (data) {
            swalInit.fire({
                title: 'Good job!',
                text: data,
                type: 'success',
                showCloseButton: true
            });
            $('#main')[0].reset();
            $("#main").validate().reset();
            $('#AddRecord').modal('hide');
            $('#projectList').DataTable().ajax.reload();
//            project.statistics().ajax.reload();

        }, error: function (data) {
            console.log(data);
            for (var i in data) {
//                var error = $('<div/>', {class: 'alert alert-danger'}).html(data[i]);
//                $('#' + i).after(error);
//                 console.log(data.errors);
            }

        }
    });
});

//PROJECTLIST DATATABLE..............................................
project.viewProject = function () {
    if (!$.fn.DataTable.isDataTable('#projectList')) {
        $("#projectList").DataTable({
            "ajax": {
                "url": "/projectlist",
                "type": "GET",
                "dataSrc": ""
            },
            "columns": [
                {"data": "number"},
                {"data": "artistename"},
                {"data": "songtitle"},
                {"data": "projecttype"},
                {"data": "activitytype"},
                {"data": "genre"},
                {"data": "projectstartdate"},
                {"data": null, "render": function (data) {
                        if (data.ispdfexcelcreated === true) {
                            return  '<div class="text-center"><div class="list-icons"> <div class="dropdown"><a href="#" class="list-icons-item" data-toggle="dropdown"><i class="icon-menu9"></i></a>\n\
                          <div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item viewbtn"> <i class="icon-eye"></i> <span class="badge badge-success">View</span></a>\n\
                          <a class="dropdown-item editbtn"><i class="icon-pencil"></i> <span class="badge badge-info">Update</span></a><a  class="dropdown-item" id="deleteprojbtn" ><i class="icon-trash"></i> <span class="badge badge-warning">Delete</span></a></div></div></div></div>'
                        } else {
                            return '<div class="text-center"><div class="list-icons"> <div class="dropdown"><a href="#" class="list-icons-item" data-toggle="dropdown"><i class="icon-menu9"></i></a>\n\
                               <div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item viewbtn"> <i class="icon-eye"></i> <span class="badge badge-success">View</span></a><a  class="dropdown-item editbtn"><i class="icon-pencil"></i> <span class="badge badge-info">Update</span></a>\n\
                               <a class="dropdown-item" id="deleteprojbtn" ><i class="icon-trash"></i> <span class="badge badge-warning">Delete</span></a><a class="dropdown-item invoicebtn"><i class="icon-plus3"></i> <span class="badge badge-success ">Create invoice</span></a></div></div></div></div>'
                        }
                    }
                }
            ]
        });
        var projTable = $('#projectList').DataTable();

        //EDIT PROJECT........................................................
        projTable.on('click', '.editbtn', function () {
            $tr = $(this).closest('tr');
            var data = projTable.row($tr).data();
            $.ajax({
                url: "getProject/" + data.id,
                type: "GET",
                success: function (data) {
                    $('#projup').val(data.id);
                    $('.activitytypead').val(data.activitytype);
                    $('.projecttypead').val(data.projecttype);
                    $('.combinationad').val(data.combination);
                    $('.genread').val(data.genre);
//                    $('#id').val(data.id);
                    $('#artistename').val(data.artistename);
                    $('#songtitle').val(data.songtitle);
                    $('#writer').val(data.writer);
                    $('#producer').val(data.producer);
                    $('#engineer').val(data.engineer);
                    $('#projectstartdate').val(data.projectstartdate);
                    $('#town').val(data.town);
                    $('#email').val(data.email);
                    $('#country').val(data.country);
                    $('#phone').val(data.phone);
                    $('#number').val(data.number);
                    $('#EditRecord').modal('show');
                }
            });
        });
        $('#editformid').on('submit', function (event) {
            event.preventDefault();
            var data = {};
            $.each(this, function (i, v) {
                var input = $(v);
                data[input.attr("name")] = input.val();
            });
            var id = $('#projup').val();
            $.ajax({
                type: "PUT",
                url: "/update_project/" + id,
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function (response) {
                    $('#EditRecord').modal('hide');
                    swalInit.fire({
                        title: 'Good job!',
                        text: 'Project Updated successfully!',
                        type: 'success',
                        showCloseButton: true
                    });
                    $('#projectList').DataTable().ajax.reload();
                }
            });
        });

//VIEW PROJECT..............................................
        projTable.on('click', '.viewbtn', function () {
            $tr = $(this).closest('tr');
            var data = projTable.row($tr).data();
            $('#artistenameview').val(data.artistename);
            $('#songtitleview').val(data.songtitle);
            $('#writerview').val(data.writer);
            $('#producerview').val(data.producer);
            $('#engineerview').val(data.engineer);
            $('#projectstartdateview').val(data.projectstartdate);
            $('#townview').val(data.town);
            $('#emailview').val(data.email);
            $('#countryview').val(data.country);
            $('#phoneview').val(data.phone);
            $('#numberview').val(data.number);
            $('#projecttypeview').val(data.projecttype);
            $('#activitytypeview').val(data.activitytype);
            $('#genreview').val(data.genre);
            $('#combinationview').val(data.combination);
            $('#ViewDetail').modal('show');
        });
//DELETE PROJECT...........................................
        $(document).on('click', '#deleteprojbtn', function () {
            $tr = $(this).closest('tr');
            var data = projTable.row($tr).data();
            var id = data.id;
            project.deleteProject(id);
        });
        project.deleteProject = function (res) {
            url = "/delete_project/";
            swalInit.fire({
                title: 'Are you sure?',
                text: "You won't be able to recover this invoice!",
                type: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'No, cancel!',
                confirmButtonClass: 'btn btn-success',
                cancelButtonClass: 'btn btn-danger',
                buttonsStyling: false
            }).then(function (result) {
                if (result.value) {
                    $.ajax({
                        type: "GET",
                        contentType: "application/json",
                        url: url + res,
                        success: function () {
                            swalInit.fire(
                                    'Deleted!',
                                    'Your record has been deleted.',
                                    'success'
                                    );
                            $('#projectList').DataTable().ajax.reload();
                        }
                    });
                } else if (result.dismiss === swal.DismissReason.cancel) {
                    swalInit.fire(
                            'Cancelled',
                            'Your imaginary file is safe :)',
                            'error'
                            );
                }
            });
        };
//ADD INVOICE...............................................
        projTable.on('click', '.invoicebtn', function () {
            $tr = $(this).closest('tr');
            var data = projTable.row($tr).data();
            var id = data.id;
            $('#myid').val(id);
            $('#AddInvoice').modal('show');
            $('#eer').on('click', function () {
                $.ajax({
                    url: "/save-invoice",
                    type: "POST",
                    data: $('#invice').serialize(),
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 200) {
                            swalInit.fire({
                                title: 'Good job!',
                                text: data.message,
                                type: 'success',
                                showCloseButton: true
                            });
                            $('#invice')[0].reset();
                            projTable.ajax.reload();
                            $('#AddInvoice').modal('hide');

                        } else {
                            swalInit.fire(
                                    'Cannot cerate Invoice',
                                    ' invoice is already genrated for this project',
                                    'info'
                                    );
                        }
                        ;
                    }
                });
            });

        });
    }
};
//VIEW INVOICE IN TABLE
project.viewInvoice = function () {
    if (!$.fn.DataTable.isDataTable('.invoicellist')) {
        $(".invoicellist").DataTable({
            "ajax": {
                "url": "/invoicelist",
                "type": "GET",
                "dataSrc": ""
            },
            "columns": [
                {"data": "number"},
                {"data": "datecreated"},
                {"data": "invoiceno"},
                {"data": null,
                    "render": function (data, type, row) {
                        return  '<div class="text-center"><div class="list-icons"> <div class="dropdown"><a href="#" class="list-icons-item" data-toggle="dropdown"><i class="icon-menu9"></i></a> \n\
                              <div class="dropdown-menu dropdown-menu-right"><a href="/invoicePDF/' + data.id + '" class="dropdown-item exportpdfbtn"> <i class="icon-eye"></i> <span class="badge badge-success">Export to pdf</span></a>\n\
                              <a class="dropdown-item editinvoicebtn"><i class="icon-file-pdf"></i> <span class="badge badge-info">Update</span></a><a  class="dropdown-item" id="deleteinvoicebtn" ><i class="icon-trash"></i> \n\
                              <span class="badge badge-warning">Delete</span></a></div></div></div></div>';
                    }
                }
            ]
        });
    }
};

project.viewInvoice();
//UPDATE INVOICE..........................................    
var invoiceTable = $('.invoicellist').DataTable();
invoiceTable.on('click', '.editinvoicebtn', function () {
    $tr = $(this).closest('tr');
    var data = invoiceTable.row($tr).data();
    var idd = data.id;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/edit_invoice/" + idd,
        success: function (data) {
            $('#idinv').val(data.id);
            $('#projectid').val(data.project.id);
            $('#studiotimecost').val(data.studiotimecost);
            $('#timeinhr').val(data.timeinhr);
            $('#mixingcost').val(data.mixingcost);
            $('#masteringcost').val(data.masteringcost);
            $('#costofintruments').val(data.costofintruments);
            $('#datecreated').val(data.datecreated);
            $('#invoiceno').val(data.invoiceno);
            $('#EditInvoice').modal('show');
        }
    });
});
$('.invbtn').on('click', function () {
    var formData = $('.editinvoiceform').serialize();
    var id = $('#idinv').val();
    $.ajax({
        type: "PUT",
        url: "/update_invoice/" + id,
        data: formData,
        success: function (response) {
            swalInit.fire({
                title: 'Good job!',
                text: 'invoice Updated successfully!',
                type: 'success',
                showCloseButton: true
            });
            $('#EditInvoice').modal('hide');
            $(".invoicellist").DataTable().ajax.reload();
        }
    });
});

//DELETE INVOICE..........................................
invoiceTable.on('click', '#deleteinvoicebtn', function () {
    $tr = $(this).closest('tr');
    var data = invoiceTable.row($tr).data();
    var idd = data.id;
    project.deleteInvoice(idd);
});
project.deleteInvoice = function (res) {
    url = "/delete_invoice/";
    swalInit.fire({
        title: 'Are you sure?',
        text: "You won't be able to recover this invoice!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'No, cancel!',
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: false
    }).then(function (result) {
        if (result.value) {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: url + res,
                success: function (data) {

                    swalInit.fire(
                            'Deleted!',
                            data.message,
                            'success'
                            );
                    $(".invoicellist").DataTable().ajax.reload();
                }
            });
        } else if (result.dismiss === swal.DismissReason.cancel) {
            swalInit.fire(
                    'Cancelled',
                    'Your imaginary file is safe :)',
                    'error'
                    );
        }
    });
};

//GET PROJECT STATISTICS ....................................
project.statistics = function () {
    $.ajax({
        url: "/projectStatics",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            $('.totalProject').html(data.ProjectCount);
            $('.vocalRecording').html(data.ProjectVocalCount);
            $('.writing').html(data.ProjectWritingCount);
            $('.engineering').html(data.ProjectEngineeringCount);
            $('.mixing').html(data.ProjectMixingCount);
            $('.production').html(data.ProjectProductionCount);
            $('.booking').html(data.booking);
        }
    });
};
project.bookingdata = function () {
    $.ajax({
        url: "/bookingdata",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            $('.booking').html(data.booking);
        }
    });
};

//setInterval(project.bookingdata, 50000);
//setInterval(reloadBooking , 50000);

function reloadBooking() {
    $("#booking").DataTable().ajax.reload();
}
//GET USER PROFILE ....................................
project.getUser = function () {
    $.ajax({
        url: "/userProfile",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            $('.username').html(data.username);
            $('.profilepic').attr("src", data.photo);
            
            $('.profilebtn').on('click', function () {
                $('#idup').val(data.id);
                $('#username').val(data.username);
                $('#password').val(data.password);
                $('#datecreat').val(data.datecreated);
                $('#ViewProfile').modal();
            });
        }
    });
};

//UPDATE USER PROFILE................................
//$("#btnSubmitprofileupdd").click(function () {
//    var form = $('#fileUploadFormuppp')[0];
//    var data = new FormData(form);
//    var jsonDataObj = {
//        "id": $("#idupdd").val(),
//        "username": $("#usernamer").val(),
//        "password": $("#passwordd").val(),
//        "datecreated": $("#datecreatt").val()
//    };
//    data.append("empJson", JSON.stringify(jsonDataObj));
//    $.ajax({
//        type: "PUT",
//        enctype: 'multipart/form-data',
//        url: "/updateprofile",
//        data: data,
//        processData: false,
//        contentType: false,
//        cache: false,
//        timeout: 600000,
//        success: function (data) {
//            swalInit.fire({
//                title: 'Good job!',
//                text: 'User profile updated successfully!',
//                type: 'success',
//                showCloseButton: true
//            });
//            $("#userlistt").DataTable().ajax.reload();
//            project.getUser().ajax.reload();
//            $('#ViewProfile').modal('hide');
//        }
//    });
//});

//UPDATE LOGGEDIN USER..............................................  
$("#btnSubmitprofileupd").click(function () {
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var jsonDataObj = {
        "id": $("#idup").val(),
        "username": $("#username").val(),
        "password": $("#password").val(),
        "datecreated": $("#datecreat").val()
    };
    data.append("empJson", JSON.stringify(jsonDataObj));
    $.ajax({
        type: "PUT",
        enctype: 'multipart/form-data',
        url: "/updateprofile",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            swalInit.fire({
                title: 'Good job!',
                text: 'User profile updated successfully!',
                type: 'success',
                showCloseButton: true
            });
            $('#ViewProfile').modal('hide');
            $("#userlistt").DataTable().ajax.reload();
            project.getUser().ajax.reload();
        }
    });
});
//VIEW USER LIST...................................................
project.viewUsers = function () {
    if (!$.fn.DataTable.isDataTable('#userlistt')) {
        $("#userlistt").DataTable({
            "ajax": {
                "url": "/userlist",
                "type": "GET",
                "dataSrc": ""
            },
            "columns": [
                {"data": "username"},
                {"data": "role"},
                {"data": "photo", "render": function (data) {
                        return '<img src="' + data + '"height="30px" width="30px"/>';
                    }
                },
                {"data": "datecreated"},
                {"data": null,
                    "defaultContent": '<div class="text-center"><div class="list-icons"><div class="dropdown"><a href="#" class="list-icons-item" data-toggle="dropdown"><i class="icon-menu9"></i></a>\n\
                                       <div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item edituserbtn"><i class="icon-pencil"></i> <span class="badge badge-info">Update</span></a>\n\
                                       <a class="dropdown-item" id="deleteuserbtn" ><i class="icon-trash"></i><span class="badge badge-warning">Delete</span></a></div></div></div></div>'
                }
            ]
        });
    }
};
project.viewUsers();
//VIEW INDIVIDUAL USERS..........................................
var userTable = $('#userlistt').DataTable();
userTable.on('click', '.edituserbtn', function () {
    $tr = $(this).closest('tr');
    var data = userTable.row($tr).data();
    $('#idupdd').val(data.id);
    $('#usernamer').val(data.username);
    $('#passwordd').val(data.password);
    $('#datecreatt').val(data.datecreated);
    $('#updateProfile').modal('show');
});
//DELETE USER....................................................
$(document).on('click', '#deleteuserbtn', function () {
    $tr = $(this).closest('tr');
    var data = userTable.row($tr).data();
    var id = data.id;
    project.deleteUsers(id);
});
project.deleteUsers = function (data) {
    url = "/delete_user/";
    swalInit.fire({
        title: 'Are you sure?',
        text: "You won't be able to recover this data!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'No, cancel!',
        confirmButtonClass: 'btn btn-success',
        cancelButtonClass: 'btn btn-danger',
        buttonsStyling: false
    }).then(function (result) {
        if (result.value) {
            $.ajax({
                type: "GET",
                contentType: "application/json",
                url: url + data,
                success: function () {
                    swalInit.fire(
                            'Deleted!',
                            'Your record has been deleted.',
                            'success'
                            );
                    $("#userlistt").DataTable().ajax.reload();
                }
            });
        } else if (result.dismiss === swal.DismissReason.cancel) {
            swalInit.fire(
                    'Cancelled',
                    'Your imaginary file is safe :)',
                    'error'
                    );
        }
    });
};
//ADD USER................................................
$("#btnSubmitt").click(function () {
    var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var jsonDataObj = {
        "username": $("#usernamee").val(),
        "password": $("#passwordd").val()
    };
    data.append("empJson", JSON.stringify(jsonDataObj));
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/profile",
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            if(data.code==202){
                swalInit.fire({
                title: 'Information',
                text: data.message,
                type: 'info',
                showCloseButton: true
            });
        }else{      
            swalInit.fire({
                title: 'Good job!',
                text: data.message,
                type: 'success',
                showCloseButton: true
            });
            $('#userlistt').DataTable().ajax.reload();
             $('#fileUploadForm')[0].reset();
             $('input[name=password]').val('');
             $('input[name=username]').val('');
             $('#AddUsers').modal('hide');
        }
    }
    });
});

//ARTISTE DATATABLE LIST......................
project.viewArtisteList = function () {
    if (!$.fn.DataTable.isDataTable('#artisteList')) {
        $("#artisteList").DataTable({
            "ajax": {
                "url": "/artistlist",
                "type": "GET",
                "dataSrc": ""
            },
            "columns": [
                {"data": "artistename"},
                {"data": "phone"},
                {"data": "email"},
                {"data": "town"},
                {"data": "country"},
                {"data": "projectstartdate"}
            ]
        });
    }
};

project.viewSongList = function () {
    if (!$.fn.DataTable.isDataTable('#songlisttable')) {
        $("#songlisttable").DataTable({
            "ajax": {
                "url": "/songlist",
                "type": "GET",
                "dataSrc": ""
            },
            "columns": [
                {"data": "songtitle"},
                {"data": "artistename"},
                {"data": "writer"},
                {"data": "producer"},
                {"data": "engineer"},
                {"data": "projectstartdate"}
            ]
        });
    }
};

//BOOKING................................................ 
project.viewBookingList = function () {
    if (!$.fn.DataTable.isDataTable('#booking')) {
        $("#booking").DataTable({
            "ajax": {
                "url": "/listbooking",
                "type": "GET",
                "dataSrc": ""
            },
            "columns": [
                {"data": "name"},
                {"data": "address"},
                {"data": "email"},
                {"data": "phone"},
                {"data": "activitytype"},
                {"data": "numberofours"},
                {"data": "datebooked"},
                {"data": null, "render": function (data) {
                        if (data.rescheduleddate === null) {
                            return '<span class="badge badge-warning">  N/A </sapn>';
                        }
                        return data.rescheduleddate;
                    }
                },
                {
                    "data": null,
                    "render": function (data) {
                        if (data.seen == 0) {//unattended to (show all buttons)
                            return'<div class="text-center"><div class="list-icons"><div class="dropdown">\n\
                               <a href="#" class="list-icons-item" data-toggle="dropdown"><i class="icon-menu9"></i></a>\n\
                               <div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item" id="confirmbookingbtn"><i class="icon-check"></i> <span class="badge badge-success">Confirm</span></a>\n\
                               <a class="dropdown-item " id="reschedulebookingbtn" ><i class="icon-undo"></i><span class="badge badge-info">Reschedule</span></a>\n\
                               <a class="dropdown-item deletebooking"><i class="icon-bin"></i><span class="badge badge-warning">Delete</span></a></div></div></div></div>'
                        } else //confirmed (show rescheduled button and cient showedup)
                            return'<div class="text-center"><div class="list-icons"><div class="dropdown"><a href="#" class="list-icons-item" data-toggle="dropdown"><i class="icon-menu9"></i></a>\n\
                                <div class="dropdown-menu dropdown-menu-right"><a class="dropdown-item" id="reschedulebookingbtn"  ><i class="icon-undo"></i><span class="badge badge-info">Reschedule</span></a>\n\
                                <a class="dropdown-item" id="signoutbtn" ><i class="icon-exit"></i><span class="badge badge-warning">Sign Out</span></a>\n\
                               </div></div></div></div>'  //<a class="dropdown-item deletebooking"><i class="icon-bin"></i><span class="badge badge-warning">Delete</span></a>
                    }
                },
                {
                    "data": "seen",
                    "render": function (data, type, row) {
                        if (data == 0) {
                            return '<a  class="confirmlink"><span class="badge badge-warning">unattended to</span></a>'
                        } else if (data == 1) {
                            return '<a "><span class="badge badge-success">confirmed</span></a>'
                        } else
                            return '<a  class="reschedulelink"><span class="badge badge-info">rescheduled</span></a> '
                    }
                }
            ]
        });
        var bookingTable = $("#booking").DataTable();
        bookingTable.on('click', '#reschedulebookingbtn', function () {
            $tr = $(this).closest('tr');
            var data = bookingTable.row($tr).data();
            var bookingid = data.id;
            $('#id').val(bookingid);
            $('#datebooked').val(data.datebooked);
            $('#datebooked').val(data.datebooked);
            $('#BookingModal').modal('show');
            $('#reschedulebtn').on('click', function () {
                $.ajax({
                    type: "PUT",
                    url: "/reschedule/" + data.id,
                    data: {
                        'seen': $('#seen').val(),
                        'rescheduleddate': $('#rescheduleddate').val(),
                        'message': $.trim($("#message").val()),
                        'datebooked': $('#datebooked').val(),
                        'dateofbooking': data.dateofbooking, //rescheduleddate
                        'email': data.email,
                        'activitytype': data.activitytype,
                        'country': data.country,
                        'phone': data.phone,
                        'address': data.address,
                        'name': data.name,
                        'numberofours': data.numberofours
                    },
                    success: function (response) {
//                        $('#rescheduleForm').reset();
                        $('#rescheduleForm')[0].reset();
                        $('#BookingModal').modal('hide');
                        $("#booking").DataTable().ajax.reload();
                        project.statistics().ajax.reload();
                    }
                });
            });
        });
        bookingTable.on('click', '#confirmbookingbtn', function () {
            $tr = $(this).closest('tr');
            var data = bookingTable.row($tr).data();
            $.ajax({
                type: "PUT",
                url: "/reschedule/" + data.id,
                data: {
                    'seen': 1,
                    'message': data.message,
                    'dateofbooking': data.dateofbooking,
                    'datebooked': data.datebooked,
                    'country': data.country,
                    'email': data.email,
                    'activitytype': data.activitytype,
                    'phone': data.phone,
                    'address': data.address,
                    'name': data.name,
                    'numberofours': data.numberofours
                },
                success: function (response) {
                    $("#booking").DataTable().ajax.reload();
                    project.statistics().ajax.reload();
                }
            });
        });
        bookingTable.on('click', '#signoutbtn', function () {
            $tr = $(this).closest('tr');
            var result = bookingTable.row($tr).data();
            $('#artistename').val(result.name);
            $('#town').val(result.town);
            $('#email').val(result.email);
            $('#country').val(result.country);
            $('#address').val(result.address);
            $('#phone').val(result.phone);
            $('.activitytypead').val(result.activitytype);
            $('#AddBookingProj').modal('show');

            $('#bookingForm').on('submit', function (event) {
                event.preventDefault();
                var data = {};
                $.each(this, function (i, v) {
                    var input = $(v);
                    data[input.attr("name")] = input.val();
                });
                $.ajax({
                    url: "/project",
                    type: "POST",
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    success: function () {
                        $.ajax({
                            url: "/delete_booking/" + result.id,
                            type: "GET",
                            success: function () {
                                $('#bookingForm')[0].reset();
                                $('#AddBookingProj').modal('hide');
                                $("#booking").DataTable().ajax.reload();
                            }
                        });
                        swalInit.fire({
                            title: 'Good job!',
                            text: 'Project added successfully!',
                            type: 'success',
                            showCloseButton: true
                        });
                    }
                });
            });
        });
        bookingTable.on('click', '.deletebooking', function () {
            $tr = $(this).closest('tr');
            var data = bookingTable.row($tr).data();
            var id = data.id;
            project.deleteBooking(id);
        });
        project.deleteBooking = function (id) {
            url = "/delete_booking/";
            swalInit.fire({
                title: 'Are you sure?',
                text: "You won't be able to recover this data!",
                type: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes, delete it!',
                cancelButtonText: 'No, cancel!',
                confirmButtonClass: 'btn btn-success',
                cancelButtonClass: 'btn btn-danger',
                buttonsStyling: false
            }).then(function (result) {
                if (result.value) {
                    $.ajax({
                        type: "GET",
                        contentType: "application/json",
                        url: url + id,
                        success: function () {
                            swalInit.fire(
                                    'Deleted!',
                                    'Your record has been deleted.',
                                    'success'
                                    );
                            $("#booking").DataTable().ajax.reload();
                            project.statistics().ajax.reload();
                        }
                    });
                } else if (result.dismiss === swal.DismissReason.cancel) {
                    swalInit.fire(
                            'Cancelled',
                            'Your imaginary file is safe :)',
                            'error'
                            );
                }
            });
        };
    }
};
//END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
