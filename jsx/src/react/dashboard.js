var WidgetList = React.createClass({
    render: function() {
        var widgetData = this.props.data.widgets;
        if (!widgetData) {
            widgetData = []
        }
        var widgetNodes = widgetData.map(function(widget) {
            var widgetClasses = "panel widget status-" + widget.widgetStatus.toLowerCase() + " col-md-3";
            return (
                <div className={widgetClasses}>
                    <div className="panel-heading">
                        <p>{widget.label}</p>
                    </div>
                    <div className="panel-body">
                    </div>
                </div>
            );
        });
        return (
            <div className="widgetList">
                {widgetNodes}
            </div>
        );
    }
});

var WidgetBox = React.createClass({
    loadCommentsFromServer: function() {
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            cache: false,
            success: function(data) {
                this.setState({data: data});
            }.bind(this),
            error: function(xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },
    getInitialState: function() {
        return {data: []};
    },
    componentDidMount: function() {
        this.loadCommentsFromServer();
        setInterval(this.loadCommentsFromServer, this.props.pollInterval);
    },
    render: function() {
        return (
            <div class="row">
                <WidgetList data={this.state.data} />
            </div>
        );
    }
});

ReactDOM.render(
    <WidgetBox url="/widgets/health" pollInterval={10000} />,
    document.getElementById('content')
);