/* ------------------------------------------------------------------------------
 *
 *  # Form validation
 *
 *  Demo JS code for form_validation.html page
 *
 * ---------------------------------------------------------------------------- */


// Setup module
// ------------------------------

var FormValidation = function() {


    //
    // Setup module components
    //

    // Uniform
    var _componentUniform = function() {
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-input-styled').uniform({
            fileButtonClass: 'action btn bg-blue'
        });
    };

    // Switchery
    var _componentSwitchery = function() {
        if (typeof Switchery === 'undefined') {
            console.warn('Warning - switchery.min.js is not loaded.');
            return;
        }

        // Initialize single switch
        var elems = Array.prototype.slice.call(document.querySelectorAll('.form-input-switchery'));
        elems.forEach(function(html) {
            var switchery = new Switchery(html);
        });
    };

    // Bootstrap switch
    var _componentBootstrapSwitch = function() {
        if (!$().bootstrapSwitch) {
            console.warn('Warning - bootstrap_switch.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-input-switch').bootstrapSwitch({
            onSwitchChange: function(state) {
                if(state) {
                    $(this).valid(true);
                }
                else {
                    $(this).valid(false);
                }
            }
        });
    };

    // Touchspin
    var _componentTouchspin = function() {
        if (!$().TouchSpin) {
            console.warn('Warning - touchspin.min.js is not loaded.');
            return;
        }

        // Define variables
        var $touchspinContainer = $('.touchspin-postfix');

        // Initialize
        $touchspinContainer.TouchSpin({
            min: 0,
            max: 100,
            step: 0.1,
            decimals: 2,
            postfix: '%'
        });

        // Trigger value change when +/- buttons are clicked
        $touchspinContainer.on('touchspin.on.startspin', function() {
            $(this).trigger('blur');
        });
    };

    // Select2 select
    var _componentSelect2 = function() {
        if (!$().select2) {
            console.warn('Warning - select2.min.js is not loaded.');
            return;
        }

        // Initialize
        var $select = $('.form-control-select2').select2({
            minimumResultsForSearch: Infinity
        });

        // Trigger value change when selection is made
        $select.on('change', function() {
            $(this).trigger('blur');
        });
    };

    // Validation config
    var _componentValidation = function() {
        if (!$().validate) {
            console.warn('Warning - validate.min.js is not loaded.');
            return;
        }

        // Initialize
        var validator = $('.form-validate').validate({
            ignore: 'input[type=hidden], .select2-search__field', // ignore hidden fields
            errorClass: 'validation-invalid-label',
            successClass: 'validation-valid-label',
            validClass: 'validation-valid-label',
            highlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            unhighlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            success: function(label) {
                label.addClass('validation-valid-label').text('Success.'); // remove to hide Success message
            },

            // Different components require proper error label placement
            errorPlacement: function(error, element) {

                // Unstyled checkboxes, radios
                if (element.parents().hasClass('form-check')) {
                    error.appendTo( element.parents('.form-check').parent() );
                }

                // Input with icons and Select2
                else if (element.parents().hasClass('form-group-feedback') || element.hasClass('select2-hidden-accessible')) {
                    error.appendTo( element.parent() );
                }

                // Input group, styled file input
                else if (element.parent().is('.uniform-uploader, .uniform-select') || element.parents().hasClass('input-group')) {
                    error.appendTo( element.parent().parent() );
                }

                // Other elements
                else {
                    error.insertAfter(element);
                }
            },
            rules: {
               
                email: {
                    email: true
                },
                repeat_email: {
                    equalTo: '#email'
                },
                               date: {
                    date: true
                },
                projectstartdate:{
                    date: true
                },
                studiotimecost:{
                    number:true
                },
                masteringcost:{
                    number:true
                },
                 costofintruments:{
                    number:true
                },
               timeinhr:{
                    digits:true
                },
                mixingcost:{
                    number:true
                },
                
                numbers: {
                    number: true
                },
                phone:{
                 digits: true,
                 
                 minlength:10,
                 maxlength:10
                }
                 
               
            }

        });

        // Reset form
        $('#reset').on('click', function() {
            validator.resetForm();
        });
    };
    
    

    //
    // Return objects assigned to module
    //

    return {
        init: function() {
            _componentUniform();
            _componentSwitchery();
            _componentBootstrapSwitch();
            _componentTouchspin();
            _componentSelect2();
            _componentValidation();
        }
    };
}();

document.addEventListener('DOMContentLoaded', function() {
    FormValidation.init();
});




























var FormValidati = function() {


    //
    // Setup module components
    //

    // Uniform
    var _componentUniform = function() {
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-input-styled').uniform({
            fileButtonClass: 'action btn bg-blue'
        });
    };

    // Switchery
    var _componentSwitchery = function() {
        if (typeof Switchery === 'undefined') {
            console.warn('Warning - switchery.min.js is not loaded.');
            return;
        }

        // Initialize single switch
        var elems = Array.prototype.slice.call(document.querySelectorAll('.form-input-switchery'));
        elems.forEach(function(html) {
            var switchery = new Switchery(html);
        });
    };

    // Bootstrap switch
    var _componentBootstrapSwitch = function() {
        if (!$().bootstrapSwitch) {
            console.warn('Warning - bootstrap_switch.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-input-switch').bootstrapSwitch({
            onSwitchChange: function(state) {
                if(state) {
                    $(this).valid(true);
                }
                else {
                    $(this).valid(false);
                }
            }
        });
    };

    // Touchspin
    var _componentTouchspin = function() {
        if (!$().TouchSpin) {
            console.warn('Warning - touchspin.min.js is not loaded.');
            return;
        }

        // Define variables
        var $touchspinContainer = $('.touchspin-postfix');

        // Initialize
        $touchspinContainer.TouchSpin({
            min: 0,
            max: 100,
            step: 0.1,
            decimals: 2,
            postfix: '%'
        });

        // Trigger value change when +/- buttons are clicked
        $touchspinContainer.on('touchspin.on.startspin', function() {
            $(this).trigger('blur');
        });
    };

    // Select2 select
    var _componentSelect2 = function() {
        if (!$().select2) {
            console.warn('Warning - select2.min.js is not loaded.');
            return;
        }

        // Initialize
        var $select = $('.form-control-select2').select2({
            minimumResultsForSearch: Infinity
        });

        // Trigger value change when selection is made
        $select.on('change', function() {
            $(this).trigger('blur');
        });
    };

    // Validation config
    var _componentValidation = function() {
        if (!$().validate) {
            console.warn('Warning - validate.min.js is not loaded.');
            return;
        }

        // Initialize
        var validator = $('.form-validate-jquery').validate({
            ignore: 'input[type=hidden], .select2-search__field', // ignore hidden fields
            errorClass: 'validation-invalid-label',
            successClass: 'validation-valid-label',
            validClass: 'validation-valid-label',
            highlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            unhighlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            success: function(label) {
                label.addClass('validation-valid-label').text('Success.'); // remove to hide Success message
            },

            // Different components require proper error label placement
            errorPlacement: function(error, element) {

                // Unstyled checkboxes, radios
                if (element.parents().hasClass('form-check')) {
                    error.appendTo( element.parents('.form-check').parent() );
                }

                // Input with icons and Select2
                else if (element.parents().hasClass('form-group-feedback') || element.hasClass('select2-hidden-accessible')) {
                    error.appendTo( element.parent() );
                }

                // Input group, styled file input
                else if (element.parent().is('.uniform-uploader, .uniform-select') || element.parents().hasClass('input-group')) {
                    error.appendTo( element.parent().parent() );
                }

                // Other elements
                else {
                    error.insertAfter(element);
                }
            },
            rules: {
               
                email: {
                    email: true
                },
                repeat_email: {
                    equalTo: '#email'
                },
                               date: {
                    date: true
                },
                projectstartdate:{
                    date: true
                },
                studiotimecost:{
                    number:true
                },
                masteringcost:{
                    number:true
                },
                 costofintruments:{
                    number:true
                },
               timeinhr:{
                    digits:true
                },
                mixingcost:{
                    number:true
                },
                
                numbers: {
                    number: true
                },
                phone:{
                 digits: true,
                 
                 minlength:10,
                 maxlength:10
                }
                 
               
            }

        });

        // Reset form
        $('#reset').on('click', function() {
            validator.resetForm();
        });
    };
    
    
    
    
   
    


    //
    // Return objects assigned to module
    //

    return {
        init: function() {
            _componentUniform();
            _componentSwitchery();
            _componentBootstrapSwitch();
            _componentTouchspin();
            _componentSelect2();
            _componentValidation();
        }
    };
}();

document.addEventListener('DOMContentLoaded', function() {
    FormValidati.init();
});







var FormValidate = function() {


    //
    // Setup module components
    //

    // Uniform
    var _componentUniform = function() {
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-input-styled').uniform({
            fileButtonClass: 'action btn bg-blue'
        });
    };

    // Switchery
    var _componentSwitchery = function() {
        if (typeof Switchery === 'undefined') {
            console.warn('Warning - switchery.min.js is not loaded.');
            return;
        }

        // Initialize single switch
        var elems = Array.prototype.slice.call(document.querySelectorAll('.form-input-switchery'));
        elems.forEach(function(html) {
            var switchery = new Switchery(html);
        });
    };

    // Bootstrap switch
    var _componentBootstrapSwitch = function() {
        if (!$().bootstrapSwitch) {
            console.warn('Warning - bootstrap_switch.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-input-switch').bootstrapSwitch({
            onSwitchChange: function(state) {
                if(state) {
                    $(this).valid(true);
                }
                else {
                    $(this).valid(false);
                }
            }
        });
    };

    // Touchspin
    var _componentTouchspin = function() {
        if (!$().TouchSpin) {
            console.warn('Warning - touchspin.min.js is not loaded.');
            return;
        }

        // Define variables
        var $touchspinContainer = $('.touchspin-postfix');

        // Initialize
        $touchspinContainer.TouchSpin({
            min: 0,
            max: 100,
            step: 0.1,
            decimals: 2,
            postfix: '%'
        });

        // Trigger value change when +/- buttons are clicked
        $touchspinContainer.on('touchspin.on.startspin', function() {
            $(this).trigger('blur');
        });
    };

    // Select2 select
    var _componentSelect2 = function() {
        if (!$().select2) {
            console.warn('Warning - select2.min.js is not loaded.');
            return;
        }

        // Initialize
        var $select = $('.form-control-select2').select2({
            minimumResultsForSearch: Infinity
        });

        // Trigger value change when selection is made
        $select.on('change', function() {
            $(this).trigger('blur');
        });
    };

    // Validation config
    var _componentValidation = function() {
        if (!$().validate) {
            console.warn('Warning - validate.min.js is not loaded.');
            return;
        }

        // Initialize
        var validator = $('.form-validation').validate({
            ignore: 'input[type=hidden], .select2-search__field', // ignore hidden fields
            errorClass: 'validation-invalid-label',
            successClass: 'validation-valid-label',
            validClass: 'validation-valid-label',
            highlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            unhighlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            success: function(label) {
                label.addClass('validation-valid-label').text('Success.'); // remove to hide Success message
            },

            // Different components require proper error label placement
            errorPlacement: function(error, element) {

                // Unstyled checkboxes, radios
                if (element.parents().hasClass('form-check')) {
                    error.appendTo( element.parents('.form-check').parent() );
                }

                // Input with icons and Select2
                else if (element.parents().hasClass('form-group-feedback') || element.hasClass('select2-hidden-accessible')) {
                    error.appendTo( element.parent() );
                }

                // Input group, styled file input
                else if (element.parent().is('.uniform-uploader, .uniform-select') || element.parents().hasClass('input-group')) {
                    error.appendTo( element.parent().parent() );
                }

                // Other elements
                else {
                    error.insertAfter(element);
                }
            },
            rules: {
               
                email: {
                    email: true
                },
                repeat_email: {
                    equalTo: '#email'
                },
                               date: {
                    date: true
                },
                projectstartdate:{
                    date: true
                },
                studiotimecost:{
                    number:true
                },
                masteringcost:{
                    number:true
                },
                 costofintruments:{
                    number:true
                },
               timeinhr:{
                    digits:true
                },
                mixingcost:{
                    number:true
                },
                
                numbers: {
                    number: true
                },
                phone:{
                 digits: true,
                 
                 minlength:10,
                 maxlength:10
                }
                 
               
            }

        });

        // Reset form
        $('#reset').on('click', function() {
            validator.resetForm();
        });
    };
    
    

    //
    // Return objects assigned to module
    //

    return {
        init: function() {
            _componentUniform();
            _componentSwitchery();
            _componentBootstrapSwitch();
            _componentTouchspin();
            _componentSelect2();
            _componentValidation();
        }
    };
}();

document.addEventListener('DOMContentLoaded', function() {
    FormValidate.init();
});














var FormValid = function() {


    //
    // Setup module components
    //

    // Uniform
    var _componentUniform = function() {
        if (!$().uniform) {
            console.warn('Warning - uniform.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-input-styled').uniform({
            fileButtonClass: 'action btn bg-blue'
        });
    };

    // Switchery
    var _componentSwitchery = function() {
        if (typeof Switchery === 'undefined') {
            console.warn('Warning - switchery.min.js is not loaded.');
            return;
        }

        // Initialize single switch
        var elems = Array.prototype.slice.call(document.querySelectorAll('.form-input-switchery'));
        elems.forEach(function(html) {
            var switchery = new Switchery(html);
        });
    };

    // Bootstrap switch
    var _componentBootstrapSwitch = function() {
        if (!$().bootstrapSwitch) {
            console.warn('Warning - bootstrap_switch.min.js is not loaded.');
            return;
        }

        // Initialize
        $('.form-input-switch').bootstrapSwitch({
            onSwitchChange: function(state) {
                if(state) {
                    $(this).valid(true);
                }
                else {
                    $(this).valid(false);
                }
            }
        });
    };

    // Touchspin
    var _componentTouchspin = function() {
        if (!$().TouchSpin) {
            console.warn('Warning - touchspin.min.js is not loaded.');
            return;
        }

        // Define variables
        var $touchspinContainer = $('.touchspin-postfix');

        // Initialize
        $touchspinContainer.TouchSpin({
            min: 0,
            max: 100,
            step: 0.1,
            decimals: 2,
            postfix: '%'
        });

        // Trigger value change when +/- buttons are clicked
        $touchspinContainer.on('touchspin.on.startspin', function() {
            $(this).trigger('blur');
        });
    };

    // Select2 select
    var _componentSelect2 = function() {
        if (!$().select2) {
            console.warn('Warning - select2.min.js is not loaded.');
            return;
        }

        // Initialize
        var $select = $('.form-control-select2').select2({
            minimumResultsForSearch: Infinity
        });

        // Trigger value change when selection is made
        $select.on('change', function() {
            $(this).trigger('blur');
        });
    };

    // Validation config
    var _componentValidation = function() {
        if (!$().validate) {
            console.warn('Warning - validate.min.js is not loaded.');
            return;
        }

        // Initialize
        var validator = $('.form-valid').validate({
            ignore: 'input[type=hidden], .select2-search__field', // ignore hidden fields
            errorClass: 'validation-invalid-label',
            successClass: 'validation-valid-label',
            validClass: 'validation-valid-label',
            highlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            unhighlight: function(element, errorClass) {
                $(element).removeClass(errorClass);
            },
            success: function(label) {
                label.addClass('validation-valid-label').text('Success.'); // remove to hide Success message
            },

            // Different components require proper error label placement
            errorPlacement: function(error, element) {

                // Unstyled checkboxes, radios
                if (element.parents().hasClass('form-check')) {
                    error.appendTo( element.parents('.form-check').parent() );
                }

                // Input with icons and Select2
                else if (element.parents().hasClass('form-group-feedback') || element.hasClass('select2-hidden-accessible')) {
                    error.appendTo( element.parent() );
                }

                // Input group, styled file input
                else if (element.parent().is('.uniform-uploader, .uniform-select') || element.parents().hasClass('input-group')) {
                    error.appendTo( element.parent().parent() );
                }

                // Other elements
                else {
                    error.insertAfter(element);
                }
            },
            rules: {
               
                email: {
                    email: true
                },
                repeat_email: {
                    equalTo: '#email'
                },
                               date: {
                    date: true
                },
//                projectstartdate:{
//                    date: true
//                },
                studiotimecost:{
                    number:true
                },
                masteringcost:{
                    number:true
                },
                 costofintruments:{
                    number:true
                },
               timeinhr:{
                    digits:true
                },
                mixingcost:{
                    number:true
                },
                
                numbers: {
                    number: true
                },
                phone:{
                 digits: true,
                 
                 minlength:10,
                 maxlength:10
                }
                 
               
            }

        });

        // Reset form
        $('#reset').on('click', function() {
            validator.resetForm();
        });
    };
    
    

    //
    // Return objects assigned to module
    //

    return {
        init: function() {
            _componentUniform();
            _componentSwitchery();
            _componentBootstrapSwitch();
            _componentTouchspin();
            _componentSelect2();
            _componentValidation();
        }
    };
}();

document.addEventListener('DOMContentLoaded', function() {
    FormValid.init();
});
