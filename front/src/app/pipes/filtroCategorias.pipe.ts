import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'filtroCategoriasPipe'
})

export class filtroCategoriasPipe implements PipeTransform{
    transform(value: any[], filtro: string):any{
        if(filtro === '' || filtro === undefined){
            return value;
        }
        return value.filter((item) => {
            return item.categoria.toLowerCase().includes(filtro.toLowerCase());
        });
    }
}