import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
    name: 'filtroRubrosPipe'
})

export class filtroRubrosPipe implements PipeTransform{
    transform(value: any[], filtro: string):any{
        if(filtro === '' || filtro === undefined){
            return value;
        }
        return value.filter((item) => {
            return item.rubro.toLowerCase().includes(filtro.toLowerCase());
        });
    }
}