exports.install = function (Vue, options) {

  var isSuccess = function (code) {
    if (code == "200") {
      return true;
    }
    return false;
  };

  Vue.prototype.isSuccess = function (code) {
    if (code == "200") {
      return true;
    }
    return false;
  };

  Vue.prototype.messageAlert = function (response) {
    if (response.data.code == "200") {
      this.$message.success(response.data.msg);
      return true;
    } else {
      this.$message.error(response.data.msg);
    }
    return false;
  };

};
