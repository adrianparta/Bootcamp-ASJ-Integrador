import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'filtroOrdenPipe'
})

export class filtroOrdenPipe implements PipeTransform{
    transform(value: any[], filtro: string, orden: string):any{
        if(filtro === '' || filtro === undefined){
            return value;
        }

        if(orden == ''){
            value.sort(function (a, b) {
              return a.id! - b.id!;
            });
          }
      
          if(orden == 'asc'){
            value.sort(function (a, b) {
              return a.total! - b.total!;
            });
          }
          if(orden == 'desc'){
            value.sort(function (a, b) {
              return b.total! - a.total!;
            });
          }

        return value.filter((item) => {
            return item.proveedor.toLowerCase().includes(filtro.toLowerCase());
        });
    }
}