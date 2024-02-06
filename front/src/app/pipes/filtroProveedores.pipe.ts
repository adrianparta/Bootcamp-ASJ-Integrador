import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'filtroProveedoresPipe'
})

export class filtroProveedoresPipe implements PipeTransform{
    transform(value: any[], filtro: string):any{
        if(filtro === '' || filtro === undefined){
            return value;
        }
        return value.filter((item) => {
            return item.razonSocial.toLowerCase().includes(filtro.toLowerCase()) || item.codigo.toLowerCase().includes(filtro.toLowerCase());
        });
    }
}