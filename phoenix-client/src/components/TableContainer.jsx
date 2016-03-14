import React from 'react';
import PureRenderMixin from 'react-addons-pure-render-mixin';

export default React.createClass({
  mixins: [PureRenderMixin],
  render: function() {
    return <div className="table-responsive">
      <table className="table table-hover">
        <thead>
          <tr>
            {this.props.headers.map(entry =>
              <th key={entry.get("id")}>{entry.get("title")}</th>
            )}
          </tr>
        </thead>
        <tbody>
          {this.props.data.map(entry => {
            var className = "default";
            if(entry.get("meta").has("rowClass")) {
              className = entry.get("meta").get("rowClass");
            }
            return <tr key={entry.get("meta").get("id")} className={className}>
              {this.props.headers.map(entry2 => {
                var ren = entry.get(entry2.get("id"));
                if(entry.get("meta").has("onclick") && entry.get("meta").get("onclick").get("id") == entry2.get("id")) {
                  ren = <button className="btn btn-primary" onClick={entry.get("meta").get("onclick").get("get")}>
                    {entry.get("meta").get("onclick").get("title")}
                  </button>;
                }
                return <td key={entry2.get("id")}>
                  {ren}
                </td>
              }
              )}
            </tr>
          }
          )}
        </tbody>
      </table>
    </div>;
  }
});
