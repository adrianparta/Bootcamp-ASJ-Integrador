import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'filtroProductosPipe'
})

export class filtroProductosPipe implements PipeTransform{
    transform(value: any[], filtro: string, ascdesc:string):any{
        if(filtro === '' || filtro === undefined){
            return value;
        }

            value.sort(function(a, b) {
            return a.nombre.localeCompare(b.nombre);
            });
            if(ascdesc == 'asc'){
                value.sort(function (a, b) {
                return a.precio - b.precio;
                });
            }
            if(ascdesc == 'desc'){
                value.sort(function (a, b) {
                return b.precio - a.precio;
                });
            }

        return value.filter((item) => {
            return item.nombre.toLowerCase().includes(filtro.toLowerCase()) || item.descripcion.toLowerCase().includes(filtro.toLowerCase());
        });
    }
}