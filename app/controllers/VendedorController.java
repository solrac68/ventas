package controllers;

import models.Vendedor;
import play.data.Form;
import play.data.FormFactory;

import play.mvc.*;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Set;
import views.html.vendedores.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.i18n.MessagesApi;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import static play.libs.Scala.asScala;

@Singleton
public class VendedorController extends Controller  {

    private final Form<VendedorData> form;
    private MessagesApi messagesApi;
    //private final List<Vendedor> vendedores;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public VendedorController(FormFactory formFactory, MessagesApi messagesApi) {
        this.form = formFactory.form(VendedorData.class);
        this.messagesApi = messagesApi;
    }

    // para todos los vendedores
    public Result index(){
        Set<Vendedor> vendedores = Vendedor.allVendedores();

        return ok(index.render(vendedores));
    }

    // para crear un vendedor
    public Result create(Http.Request request){
        //Form<Vendedor> vendedorForm = formFactory.form(Vendedor.class);
        final Form<VendedorData> boundForm = form.bindFromRequest(request);

        return ok(create.render(boundForm, request,messagesApi.preferred(request)));
    }

    // para guardar el vendedor
    public Result save(Http.Request request){
        final Form<VendedorData> boundForm = form.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.vendedores.create.render(boundForm, request, messagesApi.preferred(request)));
        } else {
            VendedorData data = boundForm.get();
            Vendedor.add(new Vendedor(data.getId(),data.getNombre(),data.getNumCelular(),LocalDate.now(),data.getEstado()));
            return redirect(routes.VendedorController.index());
        }

    }

    public Result edit(Integer id, Http.Request request){
        Vendedor vendedor = Vendedor.findById(id);
        VendedorData data;
        if(vendedor == null) {
            return notFound("Vendedor no encontrado");
        }
        data = new VendedorData();
        data.setEstado(vendedor.estado);
        data.setId(vendedor.id);
        data.setNombre(vendedor.nombre);
        data.setNumCelular(vendedor.numCelular);
        final Form<VendedorData> boundForm = form.bindFromRequest(request).fill(data);
        return ok(edit.render(boundForm, request,messagesApi.preferred(request)));

    }

    public Result update(Http.Request request){
        final Form<VendedorData> boundForm = form.bindFromRequest(request);

        if (boundForm.hasErrors()) {
            logger.error("errors = {}", boundForm.errors());
            return badRequest(views.html.vendedores.create.render(boundForm, request, messagesApi.preferred(request)));
        } else {
            VendedorData data = boundForm.get();
            Vendedor vendedorOld = Vendedor.findById(data.getId());
            if(vendedorOld == null){
                return notFound("Vendedor no encontrado");
            }
            vendedorOld.estado = data.getEstado();
            vendedorOld.id = data.getId();
            vendedorOld.nombre = data.getNombre();
            vendedorOld.numCelular = data.getNumCelular();
            return redirect(routes.VendedorController.index());
        }
    }

    public Result destroy(Integer id){

        Vendedor vendedor = Vendedor.findById(id);

        if(vendedor == null) {
            return notFound("Vendedor no encontrado");
        }

        Vendedor.remove(vendedor);

        return redirect(routes.VendedorController.index());
    }

    // Para ve detalles
    public Result show(Integer id){

        Vendedor vendedor = Vendedor.findById(id);

        if(vendedor == null) {
            return notFound("Vendedor no encontrado");
        }

        return ok(show.render(vendedor));
    }




}
