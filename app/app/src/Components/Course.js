import React, { Component } from 'react';

class Course extends Component{
    constructor(props){
      super(props);
      this.state = {
        name: this.props.name,
        prerequisites: [],
        credits: this.props.credits,
        description: this.props.name,
        isToggleOn: false
      }

      this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
      this.setState(prevState => ({
        isToggleOn: !prevState.isToggleOn
      }));
      if (this.state.isToggleOn){
        this.props.selectCourseHandler(this.state.credits,false);
      }else{
        this.props.selectCourseHandler(this.state.credits,true);
      }
    }

    isSelected(){
      return this.state.isToggleOn;
    }
  render(){
    let selected = this.state.isToggleOn

    if (selected){
      const style = {
    backgroundColor: '#4CAF50',
    color: 'white',
    textAlign: 'center',
    textDecoration: 'none',
    display: 'inline-block',
    fontSize: '16px',
    border: '1px solid black',
    width: '90px',
    fontWeight: 'bold'
      }
      return(
        <div>
        <button onClick={this.handleClick} style={style}>{this.state.name}</button>
        </div>
      )
    }else {
      const style = {
        backgroundColor: 'white',
    color: 'black',
    textAlign: 'center',
    textDecoration: 'none',
    display: 'inline-block',
    fontSize: '16px',
    width: '90px',
    fontWeight: 'bold'
      }
    return(
      <div>
      <button onClick={this.handleClick} style={style}>{this.state.name}</button>
      </div>
    )
  }
  }
}

export default Course;
