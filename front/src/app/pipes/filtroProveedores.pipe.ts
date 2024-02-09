import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'filtroProveedoresPipe'
})

export class filtroProveedoresPipe implements PipeTransform{
    transform(value: any[], filtro: string, orden:string):any{
        if(filtro === '' || filtro === undefined){
            return value;
        }

        value.sort(function(a, b) {
            return a.codigo.localeCompare(b.codigo);
        });
        switch(orden){
            case 'razonSocial':
                value.sort(function(a, b) {
                return a.razonSocial.localeCompare(b.razonSocial);
            });
            break;
            case 'asc':
                value.sort(function(a, b) {
                return (a.provincia as string).localeCompare(b.provincia as string);
            });
                value.sort(function(a, b) {
                return (a.pais as string).localeCompare(b.pais as string);
            });
            break;
            case 'desc':
                value.sort(function(a, b) {
                return (a.provincia as string).localeCompare(b.provincia as string);
            });
                value.sort(function(a, b) {
                return (b.pais as string).localeCompare(a.pais as string);
            });
            break;
            default:
            break;
        }

        return value.filter((item) => {
            return item.razonSocial.toLowerCase().includes(filtro.toLowerCase()) || item.codigo.toLowerCase().includes(filtro.toLowerCase());
        });
    }
}