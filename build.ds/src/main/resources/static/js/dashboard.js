var Widget = React.createClass({displayName: "Widget",
    render: function() {
        return (
            React.createElement("div", {className: "widget"}
            )
        );
    }
});

var WidgetList = React.createClass({displayName: "WidgetList",
    render: function() {
        var widgetData = this.props.data.widgets;
        if (!widgetData) {
            widgetData = []
        }
        var widgetNodes = widgetData.map(function(widget) {
            var widgetClasses = "panel widget status-" + widget.label.toLowerCase() + " col-md-3";
            return (
                React.createElement("div", {className: widgetClasses}, 
                    React.createElement("div", {className: "panel-heading"}, 
                        React.createElement("p", null, widget.label)
                    ), 
                    React.createElement("div", {className: "panel-body"}
                    )
                )
            );
        });
        return (
            React.createElement("div", {className: "widgetList"}, 
                widgetNodes
            )
        );
    }
});

var WidgetBox = React.createClass({displayName: "WidgetBox",
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
            React.createElement("div", {class: "row"}, 
                React.createElement(WidgetList, {data: this.state.data})
            )
        );
    }
});

ReactDOM.render(
    React.createElement(WidgetBox, {url: "/widgets", pollInterval: 5000}),
    document.getElementById('content')
);