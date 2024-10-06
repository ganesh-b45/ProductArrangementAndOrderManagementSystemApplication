package org.product.arrangement.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Category;
import model.Customer;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.SubCategory;
import service.CategoryServices;
import service.CustomerService;
import service.OrderService;
import service.ProductService;
import service.SubCategoryService;

public class ProductArrangementClientApp {
	public static void main(String args[]) {
		CategoryServices cs = new CategoryServices();
		SubCategoryService SubCObj = new SubCategoryService();
		Category c = new Category();
		SubCategory SubCModel = new SubCategory();
		ProductService ProductServiceObj = new ProductService();
		CustomerService CustomerServiceObj = new CustomerService();
		OrderService OrderServiceObj = new OrderService();

		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n");
			System.out.println("1: Add new Category");
			System.out.println("2: View Category");
			System.out.println("3: Add New Sub-Category");
			System.out.println("4: view Sub-Category");
			System.out.println("5: Add New Product Into Database As per the Sub-Category");
			System.out.println("6: View PrOducts");
			System.out.println("7: Delete Product By Product Name");
			System.out.println("8: Update Product By Product Name");
			System.out.println("9: Add New Customer");
			System.out.println("10: view Customer's ");
			System.out.println("11: Delete Customer by Customer Name");
			System.out.println("12: Update Customer by Customer Name");
			System.out.println("13: Take Order ");
			System.out.println("14: View Customer Wise Orders");
			System.out.println("15: View Product Wise Order ");
			System.out.println("16: View Order Wise Product Details");
			System.out.println("17: Show the Common Products in all Orders ");
			System.out.println("18: Search Products by Name");
			System.out.println("19: Search Products by Name, Sub-Category, or Category");
			System.out.println("0: Exit");
			System.out.println("\n");

			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				System.out.println("Enter Category name");
				sc.nextLine();
				String CategoryName = sc.nextLine();

				c.setCategory_name(CategoryName);
				boolean isAdded = cs.isAddNewCategory(c);
				if (isAdded) {
					System.out.println("New Category Added.");
				} else {
					System.out.println("Category Not Added.");
				}
				break;
			}
			case 2: {

				List<Category> list = cs.getAllCategories();
				if (list != null && !list.isEmpty()) {
					list.forEach((cc) -> System.out.println(cc.getCategory_id() + "\t" + cc.getCategory_name()));
				} else {
					System.out.println("There is no category present");
				}
				break;
			}
			case 3: {

				System.out.println("Enter the category Name ");
				sc.nextLine();
				String CategoryName = sc.nextLine();
				int CategoryId = SubCObj.getCategoryIdByCategoryName(CategoryName);
				if (CategoryId != -1) {
					System.out.println("Category IS Presnt In Database...");
					System.out.println("Now Enter Sub Category..");
//					sc.nextLine();
					String SubCategoryName = sc.nextLine();
					SubCModel.setSubcategoryName(SubCategoryName);
					SubCModel.setCategoryId(CategoryId);
					boolean b = SubCObj.isAddNewSubCategory(SubCModel);
					if (b) {
						System.out.println("Sub-Category Added Successfully in Database");
					} else {
						System.out.println("Sub-Category Not Added in Database");
					}
				} else {
					System.out.println("Category Not Found In Database...");
					System.out.println("Do You Want to Add This Category Into Database?");
					String msg = sc.nextLine();
					if (msg.equals("yes")) {
						c.setCategory_name(CategoryName);
						boolean b = cs.isAddNewCategory(c);
						if (b) {
							System.out.println("New Category Added Into Database...");
						} else {
							System.out.println("Some Problem Is There...");
						}
					} else {
						System.out.println("Thank You");
					}
				}
				break;
			}
			case 4: {

				List<SubCategory> list = SubCObj.getAllSubCategory();
				if (list != null) {
					list.forEach((cc) -> System.out.println(
							cc.getSubcategoryId() + "\t" + cc.getSubcategoryName() + "\t" + cc.getCategoryId()));
				} else {
					System.out.println("There is no Sub-Category Present");
				}

				break;
			}
			case 5: {

				sc.nextLine();
				System.out.println("Enter the Sub Category Name ");
				String SubCategoryName = sc.nextLine();
				int SubCategoryId = ProductServiceObj.getSubCategoryIdBySubCategoryName(SubCategoryName);
				if (SubCategoryId != -1) {
					System.out.println("Enter the product name");
					String ProductName = sc.nextLine();

					System.out.println("Entet the Product Prices");
					int ProductPrice = sc.nextInt();
					Product p = new Product();
					p.setProductName(ProductName);
					p.setSubCategoryId(SubCategoryId);
					p.setProductPrices(ProductPrice);

					boolean b = ProductServiceObj.isAddNewProduct(p);
					if (b) {
						System.out.println("Product Addedd Successfully in Datbase");
					} else {
						System.out.println("Product Not Addedd");
					}
				} else {
					System.out.println("Sub Category Not Found");
				}
				break;
			}
			case 6: {

				List<Product> list = ProductServiceObj.getAllProduct();
				if (list != null) {
					for (Product product : list) {
						System.out.println(product.getProductId() + "\t" + product.getProductName() + "\t"
								+ product.getSubCategoryId() + "\t" + product.getProductPrices());

					}
				} else {
					System.out.println("There is No Product Present");
				}

				break;
			}
			case 7: {

				sc.nextLine();
				System.out.println("Enter the Product Name Which You Wanna Delete/Remove from Database");
				String ProductName = sc.nextLine();
				boolean isDeleted = ProductServiceObj.DeleteProduct(ProductName);
				if (isDeleted) {
					System.out.println("Product Was Deleted");
				} else {
					System.out.println("Product deletion failed.");
				}
			}
				break;
			case 8: {
				System.out.println("Enter the Old Product Name Which You Want to Update...");
				String OldProductName = sc.next();
				System.out.println("Enter the New Product Which you Wanna replace with OldProductName...");
				String NewProductName = sc.next();
				boolean isUpdated = ProductServiceObj.updateProduct(OldProductName, NewProductName);
				if (isUpdated) {
					System.out.println("Product name was updated.");
				} else {
					System.out.println("Product name update failed.");
				}

			}
				break;
			case 9: {

				System.out.println("Enter the Customer Name");
				String CustomerName = sc.next();
				System.out.println("Enter the Customer Age");
				int Age = sc.nextInt();
				System.out.println("Enter the Customer Gender");
				String Gender = sc.next();
				System.out.println("Enter the Customer Email Address");
				String Email = sc.next();

				Customer customerModel = new Customer();
				customerModel.setCustomerName(CustomerName);
				customerModel.setAge(Age);
				customerModel.setGender(Gender);
				customerModel.setEmail(Email);

				boolean isAdded = CustomerServiceObj.isAddNewCustomer(customerModel);
				if (isAdded) {
					System.out.println("Customer Added Successfully...");
				} else {
					System.out.println("Customer Not Added");

				}
			}
				break;
			case 10: {
				List<Customer> list = CustomerServiceObj.getAllCustomer();
				if (list != null) {
					for (Customer obj : list) {
						System.out.println(obj.getCustomerId() + "\t" + obj.getCustomerName() + "\t" + obj.getAge()
								+ "\t" + obj.getGender() + "\t" + obj.getEmail());
					}
				} else {
					System.out.println("Customer's Not Found");
				}
			}
				break;
			case 11: {
				System.out.println("Enter the Customer Name Which YOu Want to Delete/Remove");
				String CustomerName = sc.next();
				boolean isUpdated = CustomerServiceObj.isDeleteCustomer(CustomerName);
				if (isUpdated) {
					System.out.println("Customer name was updated.");
				} else {
					System.out.println("Cusomer name update failed.");
				}
			}
				break;
			case 12: {
				System.out.println("Enter the Customer Which You Want to Update");
				String OldName = sc.next();
				System.out.println("Enter the Customer Name Which You Want to Replace With the OldCustomerName");
				String NewCustomerName = sc.next();
				boolean isUpdated = CustomerServiceObj.isUpdateCustomerbyName(OldName, NewCustomerName);
				if (isUpdated) {
					System.out.println("Cusomer was updated.");
				} else {
					System.out.println("Customer update failed.");
				}
			}
				break;
			case 13: {
				System.out.println("Enter the Customer Name");
				String CustomerEmail = sc.next();
				int OrderChoice = 0;
				int CustomerID = OrderServiceObj.getCustomerIdbyEmail(CustomerEmail);
				if (CustomerID != -1) {
//					System.out.println("Customer is Valid");
					Order OrderModelObj = new Order();
					OrderModelObj.setCustomerId(CustomerID);
					boolean orderAdded = OrderServiceObj.isAddOrder(OrderModelObj);
					if (orderAdded) {
						System.out.println("Order added");
					} else {
						System.out.println("Order not added");
						break;
					}
					do {
						System.out.println("Choose Category of Product which You Want to buy...");
						List<Category> catProds = cs.getAllCategories();
						for (Category obj : catProds) {
							System.out.println(obj.getCategory_name());
						}
						sc.nextLine();
						String categoryName = sc.nextLine();

						int catid = cs.getCategoryIDByName(categoryName);
						System.out.println("Choose products...");

						List<Product> products = ProductServiceObj.getProductsByCategoryName(categoryName);
						if (products != null && !products.isEmpty()) {
							System.out.println("Products in category '" + categoryName + "':");
							for (Product product : products) {
								System.out.println(product.getProductId() + "\t" + product.getProductName() + "\t"
										+ product.getProductPrices());
							}
							System.out.println("Enter the Product ID to purchase " + "or 0 to finish:");
							int ProductID = sc.nextInt();
							if (ProductID == 0) {
								break; // Exit the loop if the user enters 0
							}

							System.out.println("Enter the quantity:");
							int quantity = sc.nextInt();

							boolean detailAdded = OrderServiceObj.OrderDetail(OrderModelObj, ProductID, quantity);
							if (detailAdded) {
								System.out.println("Product added to order. " + "Your Order Placed Successfully");
							} else {
								System.out.println("Failed to add product to order.");
							}

							System.out.println("1 to take another order and 0 to Exit...");
							OrderChoice = sc.nextInt();
							sc.nextLine();

						} else {
							System.out.println("No products found in category '" + categoryName + "'.");
							System.out.println("1 to choose another category and 0 to Exit...");
							OrderChoice = sc.nextInt();
							sc.nextLine(); // Clear the newline left by nextInt()
						}
					} while (OrderChoice != 0);
				} else {
					System.out.println("Customer is Invalid");
				}

			}
				break;
			case 14: {
				System.out.println("Enter the Customer Email ID For Retrive Their Orders");
				String customerEmail = sc.next();
				System.out.println("\n");
				List<Order> orders = OrderServiceObj.getOrdersByCustomerEmail(customerEmail);
				if (orders != null && !orders.isEmpty()) {
					for (Order order : orders) {
						System.out
								.println("Order ID: " + order.getOrderId() + ", Customer ID: " + order.getCustomerId());
						List<OrderDetail> orderDetails = OrderServiceObj.getOrderDetailsByOrderId(order.getOrderId());
						if (orderDetails != null && !orderDetails.isEmpty()) {
							for (OrderDetail detail : orderDetails) {
								System.out.println(
										"Product ID: " + detail.getProductId() + ", Quantity: " + detail.getQuantity());
								System.out.println("\n");
							}
						} else {
							System.out.println("No details found for Order ID: " + order.getOrderId());
						}
					}

				} else {
					System.out.println("No orders found for customer with email " + customerEmail);
				}

			}
				break;
			case 15: {
				System.out.println("Enter the Product Name to Retrieve Orders");
				String productName = sc.next();
				System.out.println("\n");
				boolean productExists = OrderServiceObj.isProductExistsByName(productName);

				if (!productExists) {
					System.out.println("No product found with the given name.");
					break;
				}
				List<Order> orders = OrderServiceObj.getOrdersByProductName(productName);
				if (orders != null && !orders.isEmpty()) {
					for (Order order : orders) {
						System.out
								.println("Order ID: " + order.getOrderId() + ", Customer ID: " + order.getCustomerId());
						List<OrderDetail> orderDetails = OrderServiceObj.getOrderDetailsByOrderId(order.getOrderId());
						if (orderDetails != null && !orderDetails.isEmpty()) {
							for (OrderDetail detail : orderDetails) {
								System.out.println(
										"Product ID: " + detail.getProductId() + ", Quantity: " + detail.getQuantity());
							}
						} else {
							System.out.println("No details found for Order ID: " + order.getOrderId());
						}
					}
				} else {
					System.out.println("No orders found for the given product name.");
				}

			}
				break;
			case 16: {
				System.out.println("Enter the Order ID For " + "Retrieving Product Details:");
				int orderId = sc.nextInt();
				List<Product> products = OrderServiceObj.getProductDetailsByOrderId(orderId);
				if (products != null && !products.isEmpty()) {
					for (Product product : products) {
						System.out.println("Product ID: " + product.getProductId() + ", Product Name: "
								+ product.getProductName() + ", Price: " + product.getProductPrices());
					}
				} else {
					System.out.println("No products found for Order ID: " + orderId);
				}
			}
				break;
			case 17: {
				System.out.println("Common products in all orders:");
				List<Product> commonProducts = OrderServiceObj.getCommonProductsInAllOrders();
				if (commonProducts != null && !commonProducts.isEmpty()) {
					for (Product product : commonProducts) {
						System.out.println(product.getProductId() + "\t" + product.getProductName() + "\t"
								+ product.getProductPrices());
					}
				} else {
					System.out.println("No common products found in all orders.");
				}
				break;
			}
			case 18: {
				System.out.println("Enter product name, sub-category name, or category name:");
				String input = sc.next();
				List<Product> productsByName = ProductServiceObj.getProductsByName(input);
//              List<Product> productsBySubCategory = ProductServiceObj.getProductsBySubCategoryId(getSubCategoryIdByName(input));
//              List<Product> productsByCategory = productService.getProductsByCategoryId(getCategoryIdByName(input));

				if (productsByName != null && !productsByName.isEmpty()) {
					System.out.println("Showing results for: " + input);
					for (Product product : productsByName) {
						System.out.println(product.getProductName() + " \t " + product.getProductPrices());
					}
				}

			}

				break;

			case 19: {

				System.out.println("Enter product name, sub-category name, or category name:");
				String input = sc.next();

				List<Product> productsByName = ProductServiceObj.getProductsByName(input);
				List<Product> productsBySubCategory = ProductServiceObj.getProductsBySubCategoryName(input);
				List<Product> productsByCategory = ProductServiceObj.getProductsByCategoryName(input);

				if ((productsByName != null && !productsByName.isEmpty())
						|| (productsBySubCategory != null && !productsBySubCategory.isEmpty())
						|| (productsByCategory != null && !productsByCategory.isEmpty())) {

					if (productsByName != null && !productsByName.isEmpty()) {
						System.out.println("Showing results for: " + input);
						System.out.println("Products matching the name \"" + input + "\":");
						for (Product product : productsByName) {
							System.out.println(product.getProductName() + " \t " + product.getProductPrices());
						}
					}

					if (productsBySubCategory != null && !productsBySubCategory.isEmpty()) {
						System.out.println("Showing results for: " + input);
						for (Product product : productsBySubCategory) {
							System.out.println(product.getProductName() + " \t " + product.getProductPrices());
						}
					}

					if (productsByCategory != null && !productsByCategory.isEmpty()) {
						System.out.println("Showing results for: " + input);
						for (Product product : productsByCategory) {
							System.out.println(product.getProductName() + " \t " + product.getProductPrices());
						}
					}
				} else {
					System.out.println("Sorry, no results found!\r\n"
							+ "Please check the spelling or try searching for something else");
				}
			}
				break;

			case 0: {
				System.out.println("Exiting...");
				sc.close();
				System.exit(0);
			}
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}

		} while (true);
	}

}
