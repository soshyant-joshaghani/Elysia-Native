using ElysiaNative.Client.Modules.Global;
using Microsoft.UI.Xaml;

namespace ElysiaNative.Client;

public partial class App : Application
{
    public App()
    {
        InitializeComponent();
    }

    protected override void OnLaunched(LaunchActivatedEventArgs args)
    {
        new MainWindow().Activate();
    }
}
