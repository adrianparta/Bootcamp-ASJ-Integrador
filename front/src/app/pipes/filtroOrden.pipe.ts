import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'filtroOrdenPipe'
})

export class filtroOrdenPipe implements PipeTransform{
    transform(value: any[], filtro: string):any{
        if(filtro === '' || filtro === undefined){
            return value;
        }
        return value.filter((item) => {
            return item.proveedor.toLowerCase().includes(filtro.toLowerCase());
        });
    }
}