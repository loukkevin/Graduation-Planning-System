import React, { Component } from 'react';
import Course from './Course';

class PlanPage extends Component {
  constructor(){
    super();
    this.state = {
      requirements: [],
      electiveCourses: []
    }
  }

  render(){
    const planPageStyle = {
      height: 'inherit',
      width: 'inherit',
      margin: 'auto',
      padding: '5px'
    }
    const upperSectionDivStyle = {
      height: '700px',
      width: '100%',
      border: '1px solid black',
      margin: 'auto'
    }
    const lowerSectionDivStyle = {
      height: '200px',
      width: '100%',
      border: '1px solid black',
      overflow: 'auto'
    }
    const electiveCoursesDivStyle = {
      height: 'inherit',
      width: '20%',
      scroll: 'auto',
      border: '1px solid black'
    }
    const planDivStyle = {
      height: 'inherit',
      width: '80%',
      scroll: 'auto',
      border: '1px solid black'
    }
    const infoPanelStyle = {
      height: 'inherit',
      width: '50%',
      scroll: 'auto',
      overflow: 'auto',
      border: '1px solid black'
        }
    const optionPanelStyle = {
      height: 'inherit',
      width: '50%',
      scroll: 'auto',
      overflow: 'auto',
      border: '1px solid black'
            }
    return (
      <div className="PlanPage" style={planPageStyle}>
      <table  style={upperSectionDivStyle}>
      <tr>
        <td style={electiveCoursesDivStyle}>
             Chosen Electives go here
          </td>
          <td style={planDivStyle}>
          Planning table goes here
          </td>
        </tr>
        </table>
        <table style={lowerSectionDivStyle}>
        <tr >
        <td style={infoPanelStyle}> INFO PANEL
        </td>
        <td style={optionPanelStyle}>OPIONS PANEL
        </td>
        </tr>
        </table>
      </div>
    )
  }
}
export default PlanPage;
