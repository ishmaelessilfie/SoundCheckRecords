/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global Document, swal */

var project = {};

var swalInit = swal.mixin({
    buttonsStyling: false,
    confirmButtonClass: 'btn btn-primary',
    cancelButtonClass: 'btn btn-light'
});
//GET PROJECT TYPE INTO SELECTION BOX....................................
$('document').ready(function () {
    $.ajax({
        url: "/projecttypeList",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            var dataSet = data;
            $.each(dataSet, function (i, item) {
                $('#projecttypead').append($('<option>', {
                    value: item.id,
                    text: item.projecttype
                }));
            });
        }
    });
//GET ACTIVITY TYPE INTO SELECTION BOX....................................
    $.ajax({
        url: "/activitytypeList",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            var dataSet = data;
            $.each(dataSet, function (i, item) {
                $('#activitytypead').append($('<option>', {
                    value: item.id,
                    text: item.activitytype
                }));
            });
        }
    });
//GET GENRE INTO SELECTION BOX....................................
    $.ajax({
        url: "/genreList",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            var dataSet = data;
            $.each(dataSet, function (i, item) {
                $('#genread').append($('<option>', {
                    value: item.id,
                    text: item.genre
                }));
            });
        }
    });
//GET COMBINATION INTO SELECTION BOX....................................
    $.ajax({
        url: "/combinationList",
        type: "GET",
        dataType: 'json',
        success: function (data) {
            var dataSet = data;
            $.each(dataSet, function (i, item) {
                $('#combinationad').append($('<option>', {
                    value: item.id,
                    text: item.combination
                }));
            });
        }
    });
//ADD PROJECT..........................................................
    $('#main').on('submit', function (e) {
        e.preventDefault();
        var formData = $(this).serialize();
        console.log(formData);
        $.ajax({
            url: "/project",
            type: "POST",
            data: formData,
            dataType: "json",
            success: function (data) {
//              document.getElementById("main").reset();
                 swalInit.fire({
                    title: 'Good job!',
                    text: 'Project added successfully!',
                    type: 'success',
                    showCloseButton: true
                });
               
                        

            }
        });
    });
//EDIT PROJECT..............................................
    $('.editbtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (projList, status) {
            $('#id').val(projList.id);
            $('#number').val(projList.number);
            $('#artistename').val(projList.artistename);
            $('#songtitle').val(projList.songtitle);
            $('#projecttype').val(projList.projecttype.id);
            $('#activitytype').val(projList.activitytype.id);
            $('#combination').val(projList.combination.id);
            $('#genre').val(projList.genre.id);
            $('#writer').val(projList.writer);
            $('#producer').val(projList.producer);
            $('#engineer').val(projList.engineer);
            $('#projectstartdate').val(projList.projectstartdate);
            $('#town').val(projList.town);
            $('#budget').val(projList.budget);
            $('#email').val(projList.email);
            $('#country').val(projList.country);
            $('#phone').val(projList.phone);
        });
        $('#EditRecord').modal();
    });

    $('#editformid').on('submit', function (e) {
        e.preventDefault();
        var id = $('#id').val();
        $.ajax({
            type: "PUT",
            url: "/update_project/" + id,
            data: $('#editformid').serialize(),
            success: function (response) {
                console.log(response);
                location.reload();
                $('#EditRecord').modal('hide');
                swalInit.fire({
                    title: 'Good job!',
                    text: 'Project Updated successfully!',
                    type: 'success',
                    showCloseButton: true
                });

            }
        });
    });

//VIEW PROJECT......................................
    $('.viewbtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (List, status) {
            $('#idd').val(List.id);
            $('#artistenameview').val(List.artistename);
            $('#songtitleview').val(List.songtitle);
            $('#projecttypeview').val(List.projecttype.id);
            $('#activitytypeview').val(List.activitytype.id);
            $('#combinationview').val(List.combination.id);
            $('#genreview').val(List.genre.id);
            $('#writerview').val(List.writer);
            $('#producerview').val(List.producer);
            $('#engineerview').val(List.engineer);
            $('#projectstartdateview').val(List.projectstartdate);
            $('#townview').val(List.town);
            $('#budgetview').val(List.budget);
            $('#emailview').val(List.email);
            $('#countryview').val(List.country);
            $('#phoneview').val(List.phone);
        });
        $('#ViewDetail').modal();
    });

//EDIT INVOICE..........................................................
    $('.editbtninv').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (projList, status) {
            $('#idinv').val(projList.id);
            $('#projectid').val(projList.project.id);
            $('#studiotimecost').val(projList.studiotimecost);
            $('#timeinhr').val(projList.timeinhr);
            $('#mixingcost').val(projList.mixingcost);
            $('#masteringcost').val(projList.masteringcost);
            $('#costofintruments').val(projList.costofintruments);
        });
        $('#EditInvoice').modal();
    });


//ADD INVOICE........................................
    $('.invoicebtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (projList, status) {
            $('#myid').val(projList.id);
        });
        $('#AddInvoice').modal();
        $('#eer').on('click', function () {
            swalInit.fire({
                title: 'Good job!',
                text: 'Invoice added successfully!',
                type: 'success',
                showCloseButton: true
            });
        });
    });

//ADD USER................................................
    $(' #photoButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#photoModal #employeePhoto').attr('src', href);
        $('#photoModal').modal();
    });
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
                swalInit.fire({
                    title: 'Good job!',
                    text: 'User added successfully!',
                    type: 'success',
                    showCloseButton: true
                });
                $('#AddUsers').modal('hide');
            },
            error: function (e) {

                console.log("ERROR : ", e);
            }
        });
    });
//UPDATE LOOGEDIN USER PROFILE......................................
    $('.profilebtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (profile, status) {
            $('#idup').val(profile.id);
            $('#username').val(profile.username);
            $('#password').val(profile.password);
            $('#datecreat').val(profile.datecreated);
        });
        $('#ViewProfile').modal();
    });
    $("#btnSubmitprofileupd").click(function () {
        var form = $('#fileUploadFormup')[0];
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

            }
        });
    });
    //UPDATE APP USER....................................................  
    $('.updateuser').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function (userList, status) {
            $('#idupdd').val(userList.id);
            $('#usernamer').val(userList.username);
            $('#passwordd').val(userList.password);
            $('#datecreatt').val(userList.datecreated);
        });
        $('#updateProfile').modal();
    });
    $("#btnSubmitprofileupdd").click(function () {
        var form = $('#fileUploadFormuppp')[0];
        var data = new FormData(form);
        var jsonDataObj = {
            "id": $("#idupdd").val(),
            "username": $("#usernamer").val(),
            "password": $("#passwordd").val(),
            "datecreated": $("#datecreatt").val()
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
                    text: 'User updated successfully!',
                    type: 'success',
                    showCloseButton: true
                });
                $('#updateProfile').modal('hide');
            }
        });
    });
//DELETE INVOICE............................................ 
    $(Document).on('click', '#btDelete', function () {
        project.deleteInvoice($(this).data("id"));
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
                    success: function (callback) {

                        swalInit.fire(
                                'Deleted!',
                                'Your record has been deleted.',
                                'success'
                                );
                        //  table.row($button.parents('tr')).remove().draw();
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
//DELETE PROJECT...........................................
    $(Document).on('click', '#deleteprojbtn', function () {
        project.deleteProject($(this).data("id"));
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
//DELETE USER.................................
    $(Document).on('click', '.deletebtnuser', function () {
        project.deleteUsers($(this).data("id"));
    });
    project.deleteUsers = function (res) {
        url = "/delete_user/";
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
//END,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
});






    