import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './Components/header/header.component';
import { TitleComponentComponent } from './Components/title-component/title-component.component';
import { MenuProdutoComponentComponent } from './Components/menu-produto-component/menu-produto-component.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    TitleComponentComponent,
    MenuProdutoComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
