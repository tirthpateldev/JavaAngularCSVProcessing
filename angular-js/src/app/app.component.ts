import { AfterViewInit, Component, OnInit, Renderer } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent implements  OnInit {
  dtOptions: DataTables.Settings = {};

  constructor(private renderer: Renderer, private router: Router) { }

  deleteRow(info: any): void {
    alert(info.bookingId);
    $.ajax({
    url: 'https://cors-anywhere.herokuapp.com/http://localhost:8080/deleteBookings?username=admin&password=changeme&id='+info.bookingId,
    method: 'DELETE',
    contentType: 'application/json',
    success: function(result) {
        // handle success
        window.location = window.location;
    },
    error: function(request,msg,error) {
        // handle failure
    }
  });
   
  }

  ngOnInit(): void {
    this.dtOptions = {
      ajax:'https://cors-anywhere.herokuapp.com/http://localhost:8080/bookings',
      columns: [{
        title: 'Booking ID',
        data: 'bookingId'
      }, {
        title: 'Card Number',
        data: 'cardNumber'
      }, {
        title: 'Action',
        render: function (data: any, type: any, full: any) {
          return '<button> delete </button>';
        }
      }],
      rowCallback: (row: Node, data: any[] | Object, index: number) => {
        const self = this;
        $('td', row).unbind('click');
        $('button', row).bind('click', () => {
          self.deleteRow(data);
        });
        return row;
      }
    };
  }

}