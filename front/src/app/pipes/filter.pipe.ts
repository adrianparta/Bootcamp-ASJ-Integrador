import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'filterPipe'
})

export class filterPipePipe implements PipeTransform{
    transform(value: any[], filtro: string):any{
        if(filtro === '' || filtro === undefined){
            return value;
        }
        return value.filter((item) => {
            return item.nombre.toLowerCase().includes(filtro.toLowerCase()) || item.descripcion.toLowerCase().includes(filtro.toLowerCase());
        });
    }
}