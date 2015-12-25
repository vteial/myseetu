function SessionService() {
    this.context = {};
};

SessionService.prototype.processProperties = function(props) {
    console.log('processing session properties started...');
    _.assign(this.context, props);
    console.log('processing session properties finished...');
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

    var xhrs = this.xhrSuccess, xhre = this.xhrError, self = this;

    var req = phonon.ajax({
        method: 'GET',
        url: '/sessions/properties',
        dataType: 'json',
        success: function (res, xhr) {
            if(res.type === 0) {
                self.processProperties(res.data);
            }
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

SessionService.prototype.changeDetails = function (user, scb, ecb) {
    console.log('change-details started...');

    var xhrs = this.xhrSuccess, xhre = this.xhrError, self = this;

    var req = phonon.ajax({
        method: 'POST',
        url: '/sessions/change-details',
        dataType: 'json',
        contentType: 'application/json',
        data: user,
        success: function (res, xhr) {
            if(res.type === 0) {
                var sdto = self.context.sessionDto;
                sdto.firstName = user.firstName;
                sdto.lastName = user.lastName;
            }
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

    console.log('change-details finished...');
};

SessionService.prototype.changePassword = function (user, scb, ecb) {
    console.log('change-password started...');

    var xhrs = this.xhrSuccess, xhre = this.xhrError, self = this;

    var req = phonon.ajax({
        method: 'POST',
        url: '/sessions/change-password',
        dataType: 'json',
        contentType: 'application/json',
        data: user,
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

    console.log('change-password finished...');
};

SessionService.prototype.signOut = function (scb, ecb) {
    console.log('sign-out started...');

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

    console.log('sign-out finished...');
};

