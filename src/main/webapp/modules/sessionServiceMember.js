function SessionService() {
    this.context = {};
};

SessionService.prototype.xhrSuccess = function (res, xhr) {
    console.log(res);
};

SessionService.prototype.xhrError = function (res, flagError, xhr) {
    console.error(flagError);
    console.error(res);
};

SessionService.prototype.properties = function (scb, ecb) {
    console.log('properties started...');

    var xhrs = this.xhrSuccess, xhre = this.xhrError;

    var req = phonon.ajax({
        method: 'GET',
        url: '/sessions/properties',
        dataType: 'json',
        success: function (res, xhr) {
            xhrs(res, xhr);
            if (scb) {
                scb(res, xhr);
            }
        },
        error: function (res, flagError, xhr) {
            xhre(res, flagError, xhr);
            if (ecb) {
                ecb(res, flagError, xhr);
            }
        },
    });

    console.log('properties finished...');
};

SessionService.prototype.signOut = function (scb, ecb) {
    console.log('signOut started...');

    var xhrs = this.xhrSuccess, xhre = this.xhrError;

    var req = phonon.ajax({
        method: 'GET',
        url: '/sessions/sign-out',
        dataType: 'json',
        success: function (res, xhr) {
            xhrs(res, xhr);
            if (scb) {
                scb(res, xhr);
            }
        },
        error: function (res, flagError, xhr) {
            xhre(res, flagError, xhr);
            if (ecb) {
                ecb(res, flagError, xhr);
            }
        },
    });

    console.log('signOut finished...');
};

