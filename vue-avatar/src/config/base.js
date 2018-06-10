exports.install = function (Vue, options) {

  var isSuccess = function (code) {
    if (code == "200") {
      return true;
    }
    return false;
  };

  Vue.prototype.isSuccess = function (code) {
    return isSuccess(code)
  };

  Vue.prototype.messageAlert = function (response) {
    if (isSuccess(response.data.code)) {
      this.$message.success(response.data.msg);
    } else {
      this.$message.error(response.data.msg);
    }
  };

};
