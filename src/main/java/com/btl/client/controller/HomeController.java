package com.btl.client.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.btl.entities.Account;
import com.btl.entities.AccountDetails;
import com.btl.entities.ERole;
import com.btl.entities.Order;
import com.btl.entities.Product;
import com.btl.entities.User;
import com.btl.repositories.AccountRepository;
import com.btl.services.AccountService;
import com.btl.services.CartService;
import com.btl.services.CategoryService;
import com.btl.services.ProductService;
import com.btl.services.SlideImageService;
import com.btl.repositories.RoleRepository;
import com.btl.entities.Role;
import com.btl.models.Item;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    SlideImageService slideImageService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    CartService cartService;
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository; // Inject RoleRepository


    @GetMapping({ "/", "/index.html" })
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "home/index";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") @Valid User user,
                                  BindingResult result,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        if (!user.getPassword().equals(user.getRepassword())) {
            result.rejectValue("repassword", "error.user", "Mật khẩu nhập lại không khớp");
        }

        if (accountRepository.findByUsername(user.getUsername()).isPresent()) {
            result.rejectValue("username", "error.user", "Tên đăng nhập đã tồn tại");
        }

        if (accountRepository.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", "error.user", "Email đã được đăng ký");
        }

        if (result.hasErrors()) {
            model.addAttribute("registerModal", true);
            return "home/index";
        }

        // Tạo tài khoản mới
        Account newAcc = new Account();
        newAcc.setAccountId(UUID.randomUUID());
        newAcc.setFirstname(user.getFirstname());
        newAcc.setLastname(user.getLastname());
        newAcc.setEmail(user.getEmail());
        newAcc.setUsername(user.getUsername());
        newAcc.setPassword(passwordEncoder.encode(user.getPassword()));
        newAcc.setEnabled(true);

     // Gán vai trò mặc định ROLE_USER
        Role defaultRole = roleRepository.findByRoleName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("ROLE_USER không tồn tại trong database"));
        newAcc.getRoles().add(defaultRole); // Thêm vào set roles


        accountRepository.save(newAcc);

        redirectAttributes.addFlashAttribute("registerSuccess", true);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "home/login";
    }

    @GetMapping("/success")
    public String success() {
        try {
            AccountDetails account = (AccountDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            if (account.getAuthorities().toString().contains(ERole.ROLE_ADMIN.name())) {
                return "redirect:/backend";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping({ "/404.html", "/error" })
    public String error() {
        return "home/404";
    }

    @GetMapping("/blog-article.html")
    public String blogArticle() {
        return "home/blog-article";
    }

    @GetMapping("/blog-fullwidth.html")
    public String blogFullwidth() {
        return "home/blog-fullwidth";
    }

    @GetMapping("/blog-grid.html")
    public String blogGrid() {
        return "home/blog-grid";
    }

    @GetMapping("/blog-list.html")
    public String blogList() {
        return "home/blog-list";
    }

    @GetMapping("/coming-soon.html")
    public String comingSoon() {
        return "home/coming-soon";
    }

    @GetMapping("/documentation.html")
    public String documentation() {
        return "home/documentation";
    }

    @GetMapping("/elements.html")
    public String elements() {
        return "home/elements";
    }

    @GetMapping("/forum.html")
    public String forum() {
        return "home/forum";
    }

    @GetMapping("/forum-single-topic.html")
    public String forumSingleTopic() {
        return "home/forum-single-topic";
    }

    @GetMapping("/forum-topics.html")
    public String forumTopics() {
        return "home/forum-topics";
    }

    @GetMapping("/gallery.html")
    public String gallery() {
        return "home/gallery";
    }

    @GetMapping("/news.html")
    public String news() {
        return "home/news";
    }

    @GetMapping("/offline.html")
    public String offline() {
        return "home/offline";
    }


    @GetMapping("/store-cart.html")
    public String storeCart() {
        return "home/store-cart";
    }

    @GetMapping("/store-catalog.html")
    public String storeCatalog() {
        return "home/store-catalog";
    }

    @GetMapping("/store-catalog-alt.html")
    public String storeCatalogAlt() {
        return "home/store-catalog-alt";
    }

    @GetMapping("/store-checkout")
    public String storeCheckout(Model model) {
        model.addAttribute("user", new User());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        Optional<Account> acc = accountService.getAccountByUsername(username);
        if (acc.isPresent()) {
            model.addAttribute("acc", acc.get());
        } else {
            model.addAttribute("acc", new Account()); // fallback tránh lỗi khi chưa đăng nhập
        }
        return "home/store-checkout";
    }

    @GetMapping("/store-product.html")
    public String storeProduct() {
        return "home/store-product";
    }

    @GetMapping("/tournaments.html")
    public String tournaments() {
        return "home/tournaments";
    }

    @GetMapping("/tournaments-teammate.html")
    public String tournamentsTeammate() {
        return "home/tournaments-teammate";
    }

    @GetMapping("/tournaments-teams.html")
    public String tournamentsTeams() {
        return "home/tournaments-teams";
    }

    @GetMapping("/widgets.html")
    public String widgets() {
        return "home/widgets";
    }

    @GetMapping("/store.html")
    public String storePage(Model model) {
        model.addAttribute("user", new User());
        List<Product> products = productService.getByActive(true);
        model.addAttribute("products", products);
        return "home/store";

    }
    
	@SuppressWarnings("unchecked")
	@PostMapping("/addcart")
	public String addcart(long productId,Model model,HttpSession session) {
		model.addAttribute("user", new User());
		List<Item> items=new ArrayList<>();
		if(session.getAttribute("cartitems")!=null)
		{
			items=(List<Item>)session.getAttribute("cartitems");
		}
		items=cartService.addCart(productService.getById(productId),items);
		session.setAttribute("cartitems", items);
		model.addAttribute("cartitems",items);
		Double total=items.stream().mapToDouble(Item::getTotal).sum();
		model.addAttribute("totalCart",total);
        return "redirect:/store.html";
	}
	
	@GetMapping("/removeCart/{id}")
	public String removeCart(@PathVariable("id") String productId, Model model, HttpSession session) {
	    System.out.println(">>> Product ID to remove: " + productId);
	    List<Item> items = new ArrayList<>();
	    if (session.getAttribute("cartitems") != null) {
	        items = (List<Item>) session.getAttribute("cartitems");
	        System.out.println(">>> Current items in cart:");
	        for (Item i : items) {
	            System.out.println(i.getProductId() + " - " + i.getProductName());
	        }
	    }

	    items = cartService.removeCart(productId, items);
	    session.setAttribute("cartitems", items);
	    model.addAttribute("cartitems", items);
	    Double total = items.stream().mapToDouble(Item::getTotal).sum();
	    model.addAttribute("totalCart", total);
        return "redirect:/store.html";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/updateCart")
	public String updateCart(String[] productids, int[] quantities, HttpSession session) {
	    List<Item> items = new ArrayList<>();
	    if (session.getAttribute("cartitems") != null) {
	        items = (List<Item>) session.getAttribute("cartitems");
	    }

	    items = cartService.updateCart(productids, quantities, items);
	    session.setAttribute("cartitems", items);

	    double total = items.stream()
	        .mapToDouble(item -> item.getPrice() * item.getQuantity())
	        .sum();
	    session.setAttribute("total", total); // gán luôn vào session

	    return "redirect:/store.html";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/checkout")
	public String checkout(Order order, Model model,HttpSession session) {
		List<Item> items=new ArrayList<>();
		if(session.getAttribute("cartitems")!=null)
		{
			items=(List<Item>)session.getAttribute("cartitems");
		}
		String msg=cartService.insertOrder(order,items);
		model.addAttribute("msg",msg);
		session.setAttribute("cartitems", new ArrayList<>());
		return "redirect:/";
	}
}


