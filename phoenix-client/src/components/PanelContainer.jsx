import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';

export default React.createClass({
  render: function() {
    var header = <div></div>;
    if(this.props.title) {
      header = <div className="panel-heading">{this.props.title}</div>;
    }
    return <div className="panel panel-default">
      {header}
      <div className="panel-body">
        {this.props.panel}
      </div>
      {this.props.footer}
    </div>;
  }
});
